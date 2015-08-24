package com.example.mikhail.cubike.handlers;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.mikhail.cubike.helpers.States;
import com.example.mikhail.cubike.interfaces.UIactions;
import com.example.mikhail.cubike.managers.RequestManager;
import com.example.mikhail.cubike.model.Place;
import com.example.mikhail.cubike.model.Track;

import java.util.List;

/**
 * Created by Mikhail on 02.08.2015.
 */
public class MessageHandler extends Handler {

    private RequestManager manager_;
    private UIactions activity_;

    public MessageHandler(RequestManager manager) {
        this.manager_ = manager;
    }

    public void handleMessage(Message msg) {
        switch (msg.what) {
            case States.TRACKS_WERE_FOUND:
                if (msg.obj != null) {
                    List<Track> tracks = (List<Track>) msg.obj;
                    manager_.updateUI(tracks);
                }
                break;

            case States.PLACES_WERE_FOUND:
                Log.v("STATUS", "PLACES_WERE_FOUND");
                if (msg.obj != null) {
                    List<Place> tracks = (List<Place>) msg.obj;
                    Log.v("Places",Integer.toString(tracks.size()));

                    manager_.updateUI2(tracks);
                }
                break;

            default:
                break;
        }
    }
}
