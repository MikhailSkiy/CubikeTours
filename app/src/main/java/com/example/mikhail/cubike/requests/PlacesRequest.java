package com.example.mikhail.cubike.requests;

import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import com.example.mikhail.cubike.model.Place;

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
public class PlacesRequest {

    private final static String BASE_REQUEST = "http://192.168.10.2:9000/api/values/";

    private final static String TITLE_KEY = "Title";
    private final static String SHORT_DESCRIPTION_KEY = "ShortDescription";
    private final static String FULL_DESCRIPTION_KEY = "FullDescription";
    private final static String PLACE_ID_KEY = "PlaceId";
    private final static String LATITUDE_KEY = "Latitude";
    private final static String LONGITUDE_KEY = "Longitude";
    private final static String ICON_KEY = "Icon";
    private final static String TRACK_ID_KEY = "TrackId";

    private String response_;

    private static final String LOG_TAG = PlacesRequest.class.getSimpleName();

    public URL getUrl(int id){
        URL url = null;
        try {
            Uri placesUri = Uri.parse(BASE_REQUEST).buildUpon()
                    .appendPath(Integer.toString(id)).build();
            Log.v("Build URL", placesUri.toString());

            url = new URL(placesUri.toString());
        } catch (IOException e){
            Log.e(LOG_TAG,"Error",e);
            return null;
        }

        return url;
    }

    public String sendRequest(URL url) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonResult = null;
        String jsonClear = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            Log.v("Connect", "CONNECT");

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

    public List<Place> getPlacesFromJson(String response) throws JSONException {
        Log.v("Response",response);
        JSONArray trackArray = new JSONArray(response);
        List<Place> places = new ArrayList<Place>();
        for (int i=0;i<trackArray.length();i++){
            Place place = getPlace(trackArray.getJSONObject(i));
            places.add(place);
        }
        return places;
    }

    private Place getPlace(JSONObject trackObject) throws JSONException {
        String title = trackObject.getString(TITLE_KEY);
        String shortDescription = trackObject.getString(SHORT_DESCRIPTION_KEY);
        String fullDescription = trackObject.getString(FULL_DESCRIPTION_KEY);
        double latitude = trackObject.getDouble(LATITUDE_KEY);
        double longitude = trackObject.getDouble(LONGITUDE_KEY);
        int placeId = trackObject.getInt(PLACE_ID_KEY);
        // Get the icon
        byte[] icon = Base64.decode(trackObject.getString(ICON_KEY),Base64.DEFAULT);
        // Get the trackId
        int trackId = trackObject.getInt(TRACK_ID_KEY);
        Place place = new Place(placeId,title,shortDescription,fullDescription,latitude,longitude,icon,trackId);

        return place;
    }

}
