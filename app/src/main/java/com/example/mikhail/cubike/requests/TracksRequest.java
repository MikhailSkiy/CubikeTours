package com.example.mikhail.cubike.requests;

import android.util.Base64;
import android.util.Log;

import com.example.mikhail.cubike.model.Track;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikhail on 02.08.2015.
 */
public class TracksRequest {

    private final static String BASE_REQUEST = "http://192.168.10.4:9000/api/values";

    private final static String TITLE_KEY = "Title";
    private final static String DESCRIPTION_KEY = "Description";
    private final static String TRACK_ID_KEY = "TrackId";
    private final static String CITY_ID_KEY = "CityId";
    private final static String DURATION_KEY = "Duration";
    private final static String LENGTH_KEY = "Length";
    private final static String COMPLEXITY_KEY = "Complexity";
    private final static String RATING_KEY ="Rating";
    private final static String ICON_KEY ="Icon";

    private String response_;

    private static final String LOG_TAG = TracksRequest.class.getSimpleName();

    public String sendRequest() {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonResult = null;
        String jsonClear = null;

        try {
            URL url = new URL(BASE_REQUEST);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            Log.v("Connect","CONNECT");

            int responseCode = urlConnection.getResponseCode();
            Log.v("Connect",Integer.toString(responseCode));

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            jsonResult = buffer.toString();

             String s =  jsonResult.replaceAll("\\\\", "");
            jsonClear =  s.substring(1, s.length() - 1);

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return jsonClear;
    }

    public List<Track> getTracksFromJson(String response) throws JSONException{
        Log.v("Response",response);
        JSONArray trackArray = new JSONArray(response);
        List<Track> tracks = new ArrayList<Track>();
        for (int i=0;i<trackArray.length();i++){
            Track track = getTrack(trackArray.getJSONObject(i));
            tracks.add(track);
        }
        return tracks;
    }

    private Track getTrack(JSONObject trackObject) throws JSONException {
        String title = trackObject.getString(TITLE_KEY);
        String description = trackObject.getString(DESCRIPTION_KEY);
        int trackId = trackObject.getInt(TRACK_ID_KEY);
        int duration = trackObject.getInt(DURATION_KEY);
        int length = trackObject.getInt(LENGTH_KEY);
        int rating = trackObject.getInt(RATING_KEY);
        int complexity = trackObject.getInt(COMPLEXITY_KEY);
        int cityId = trackObject.getInt(COMPLEXITY_KEY);
        byte[] icon = Base64.decode(trackObject.getString(ICON_KEY), Base64.DEFAULT);
        Track track = new Track(trackId,title,description,duration,length,rating,complexity,cityId,icon);

        return track;
    }
    
}
