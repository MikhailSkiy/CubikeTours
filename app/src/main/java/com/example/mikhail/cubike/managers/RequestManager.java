package com.example.mikhail.cubike.managers;

import android.util.Log;

import com.example.mikhail.cubike.database.DatabaseHelper;
import com.example.mikhail.cubike.handlers.MessageHandler;
import com.example.mikhail.cubike.helpers.States;
import com.example.mikhail.cubike.interfaces.UIactions;
import com.example.mikhail.cubike.model.Place;
import com.example.mikhail.cubike.model.Preview;
import com.example.mikhail.cubike.model.Track;
import com.example.mikhail.cubike.requests.PlacesRequest;
import com.example.mikhail.cubike.requests.TracksRequest;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikhail on 02.08.2015.
 */
public class RequestManager {

    private MessageHandler handler_ = new MessageHandler(this);
    private UIactions clientActivity_;
    private DatabaseHelper helper_;

    public RequestManager(UIactions clienActivity){
        this.clientActivity_ = clienActivity;
    }

    public void getTracks() {
        helper_ = new DatabaseHelper(clientActivity_.getClientActivityContext());
        List<Track> tracks = helper_.getAllTracks();
        if (tracks.size() != 0) {
            handler_.sendMessage(handler_.obtainMessage(States.TRACKS_WERE_FOUND, tracks));
        } else {
            sendAsyncTracksRequest();
        }
    }

    public void getPlacesByTrackId(int trackId) {
       helper_ = new DatabaseHelper(clientActivity_.getClientActivityContext());
        List<Place> places = helper_.getPlacesByTrackId(trackId);
//        helper_.close();
        if (places.size() != 0) {
            handler_.sendMessage(handler_.obtainMessage(States.PLACES_WERE_FOUND, places));
        } else
        {
            sendAsyncPlaceRequest(trackId);
        }
    }

    public void sendAsyncTracksRequest(){
        Thread background = new Thread(new Runnable() {
            @Override
            public void run() {
                TracksRequest tracksRequest = new TracksRequest();
                String response = tracksRequest.sendRequest();
                List<Track> tracks = new ArrayList<>();
                try {
                    tracks = tracksRequest.getTracksFromJson(response);
                    // Put data into database
                    // TODO replacse it by separate method, may be using Loaders
                    helper_ = new DatabaseHelper(clientActivity_.getClientActivityContext());
                    helper_.addAllTracks(tracks);
                } catch (JSONException e) {
                    Log.e("JSONException", e.getMessage());
                }
                handler_.sendMessage(handler_.obtainMessage(States.TRACKS_WERE_FOUND, tracks));
            }
        });
        background.start();
    }

    public void sendAsyncPlaceRequest(final int id){
        Thread background = new Thread(new Runnable() {
            @Override
            public void run() {
                PlacesRequest placesRequest = new PlacesRequest();
                String response = placesRequest.sendRequest(placesRequest.getUrl(id));
                List<Place> places = new ArrayList<>();
                try {
                    places = placesRequest.getPlacesFromJson(response);
                    // Put data into database
                    // TODO replacse it by separate method, may be using Loaders
                    helper_ = new DatabaseHelper(clientActivity_.getClientActivityContext());
                    helper_.addAllPlaces(places);
                } catch (JSONException e) {
                    Log.e("JSONException", e.getMessage());
                }
                handler_.sendMessage(handler_.obtainMessage(States.PLACES_WERE_FOUND,places));
            }
        });
        background.start();
    }

    public void updateUI(List<Track> tracks){
        List<Preview> previews = new ArrayList<>();
        for (int i=0;i<tracks.size();i++){
            Preview preview = new Preview(tracks.get(i).getTrackId(),tracks.get(i).getTitle(),tracks.get(i).getDescription(),tracks.get(i).getDuration(),tracks.get(i).getLength(),tracks.get(i).getIcon());
            previews.add(preview);
        }
        clientActivity_.updateUI(previews);
    }

    public void updateUI2(List<Place> places){
        List<Preview> previews = new ArrayList<>();
        for (int i=0;i<places.size();i++){
            Preview preview = new Preview();
            preview.setTitle_(places.get(i).getTitle());
            preview.setDescription_(places.get(i).getShortDescription());
            preview.setIcon_(places.get(i).getIcon());
            preview.setLatitude(places.get(i).getLatitude());
            preview.setLongitude(places.get(i).getLongitude());
            previews.add(preview);
        }
        clientActivity_.updateUI(previews);
    }
}
