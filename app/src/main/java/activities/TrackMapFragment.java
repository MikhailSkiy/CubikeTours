package activities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.mikhail.cubike.R;
import com.example.mikhail.cubike.broadcasts.ReceiverPositioningAlarm;
import com.example.mikhail.cubike.interfaces.OnNewLocationListener;
import com.example.mikhail.cubike.managers.RequestManager;
import com.example.mikhail.cubike.model.Preview;
import com.example.mikhail.cubike.utily.UtilMethods;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class TrackMapFragment extends Fragment implements GoogleMap.OnMarkerClickListener {

    private GoogleMap map_;
    private SupportMapFragment fragment;
    RequestManager manager_;
    private int selectedTrackId;
    private int NOTIFICATION_ID = 0;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();
        fragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, fragment).commit();
        }

        // manager_ = new RequestManager((MapActivity) getActivity());
        //  manager_.getPlacesByTrackId(selectedTrackId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intentToFire =new Intent(ReceiverPositioningAlarm.ACTION_REFRESH_SCHEDULE_ALARM);
//        intentToFire.putExtra(ReceiverPositioningAlarm.COMMAND, ReceiverPositioningAlarm.SENDER_ACT_DOCUMENT);
//        getActivity().sendBroadcast(intentToFire);
//
//        OnNewLocationListener onNewLocationListener = new OnNewLocationListener() {
//            @Override
//            public void onNewLocationReceived(Location location) {
//                Log.v("Location","changed");
//                sendNotification(getView());
//               // ReceiverPositioningAlarm.clearOnNewLocationListener(this);
//            }
//        };
//        // start listening for new location
//        ReceiverPositioningAlarm.setOnNewLocationListener(onNewLocationListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (map_ == null) {
            // Try to obtain the map from the SupportMapFragment.
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            map_ = mapFragment.getMap();
            // Check if we were successful in obtaining the map.
            if (map_ != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(47.2092003, 38.9334364));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(14);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v("Action", "TrackMapFragment onCreateView");
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_map, container, false);

    }

    public void updateUI(List<Preview> places) {
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(places.get(0).getLatitude(), places.get(0).getLongitude()));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(20);

        map_.animateCamera(zoom);
        // map_.moveCamera(center);
        for (int i = 0; i < places.size(); i++) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(places.get(i).getIcon_(), 0, places.get(i).getIcon_().length);
            int width = bitmap.getWidth();
            Bitmap badge = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.badge);
            Bitmap bmp12 = UtilMethods.getRoundedCroppedBitmap(badge, width / 3);
            Bitmap bmp1 = UtilMethods.getRoundedCroppedMarker(BitmapFactory.decodeByteArray(places.get(i).getIcon_(), 0, places.get(i).getIcon_().length), (int)(width*0.6), bmp12);

            int number = places.get(i).getId_();
            String numberStr = Integer.toString(number);
            map_.addMarker(new MarkerOptions().position(
                    new LatLng(places.get(i).getLatitude(), places.get(i).getLongitude())).snippet(Integer.toString(places.get(i).getId_())).icon(
                    BitmapDescriptorFactory.fromBitmap(bmp1)).title(number + ") " + places.get(i).getTitle_()));



            Log.v("Lat", Double.toString(places.get(i).getLatitude()));
            Log.v("Lng", Double.toString(places.get(i).getLongitude()));
        }
        map_.moveCamera(center);
        map_.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);


        for (int i=0;i<places.size()-2;i++) {
            ArrayList<LatLng> twopoints = new ArrayList<>();
            twopoints.add(new LatLng(places.get(i).getLatitude(), places.get(i).getLongitude()));
            twopoints.add(new LatLng(places.get(i + 1).getLatitude(), places.get(i + 1).getLongitude()));
            new DrawWorker().execute(twopoints);
        }
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        String id = marker.getSnippet();
        int intId = Integer.parseInt(id);
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("PlaceId",intId);
        startActivity(intent);
        return true;
    }

    private class DrawWorker extends AsyncTask<ArrayList<LatLng>,Void, ArrayList<LatLng>>{

        @Override
        protected ArrayList<LatLng> doInBackground(ArrayList<LatLng>... params) {
            if (params.length == 0) {
                return null;
            }

            ArrayList<LatLng> newPoints = getDirections(params[0].get(0).latitude,params[0].get(0).longitude,params[0].get(1).latitude,params[0].get(1).longitude);

            return newPoints;
        }

        @Override
        protected void onPostExecute(ArrayList<LatLng> latLngs) {
            ArrayList<LatLng> points = new ArrayList<>();
            for (int i=0;i<latLngs.size()-2;i++){
                points.add(latLngs.get(i));
                points.add(latLngs.get(++i));
                drawPrimaryLinePath(points);
                points.clear();
            }
        }
    }

    public static ArrayList<LatLng> getDirections(double lat1, double lon1, double lat2, double lon2) {
        String url = "http://maps.googleapis.com/maps/api/directions/xml?origin=" +lat1 + "," + lon1  + "&destination=" + lat2 + "," + lon2 + "&sensor=false&units=metric";
        Log.v("URL",url);
        String tag[] = { "lat", "lng" };
        ArrayList list_of_geopoints = new ArrayList();
        HttpResponse response = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpPost httpPost = new HttpPost(url);
            response = httpClient.execute(httpPost, localContext);
            InputStream in = response.getEntity().getContent();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(in);
            if (doc != null) {
                NodeList nl1, nl2;
                nl1 = doc.getElementsByTagName(tag[0]);
                nl2 = doc.getElementsByTagName(tag[1]);
                if (nl1.getLength() > 0) {
                    list_of_geopoints = new ArrayList();
                    for (int i = 0; i < nl1.getLength(); i++) {
                        Node node1 = nl1.item(i);
                        Node node2 = nl2.item(i);
                        double lat = Double.parseDouble(node1.getTextContent());
                        double lng = Double.parseDouble(node2.getTextContent());
                        list_of_geopoints.add(new LatLng(lat,lng));
                    }
                } else {
                    // No points found
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_of_geopoints;
    }

    private void drawPrimaryLinePath(ArrayList<LatLng> listLocsToDraw) {

        if (listLocsToDraw.size() < 2) {
            return;
        }

        PolylineOptions options = new PolylineOptions();

        options.color(Color.parseColor("#CC0000FF"));
        options.width(5);
        options.visible(true);

        for (LatLng locRecorded : listLocsToDraw) {
            options.add(new LatLng(locRecorded.latitude,
                    locRecorded.longitude));
          //  map_.addMarker(new MarkerOptions().position(
              //      new LatLng(locRecorded.latitude,locRecorded.longitude)));
        }
        map_.addPolyline(options);

    }

//    public void sendNotification(View view) {
//
//        // BEGIN_INCLUDE(build_action)
//        /** Create an intent that will be fired when the user clicks the notification.
//         * The intent needs to be packaged into a {@link android.app.PendingIntent} so that the
//         * notification service can fire it on our behalf.
//         */
//
//        Intent intent = new Intent(getActivity(),  DetailsActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        // END_INCLUDE(build_action)
//
//        // BEGIN_INCLUDE (build_notification)
//        /**
//         * Use NotificationCompat.Builder to set up our notification.
//         */
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity());
//
//        /** Set the icon that will appear in the notification bar. This icon also appears
//         * in the lower right hand corner of the notification itself.
//         *
//         * Important note: although you can use any drawable as the small icon, Android
//         * design guidelines state that the icon should be simple and monochrome. Full-color
//         * bitmaps or busy images don't render well on smaller screens and can end up
//         * confusing the user.
//         */
//        builder.setSmallIcon(R.drawable.place_ranevskaya);
//
//        // Set the intent that will fire when the user taps the notification.
//        builder.setContentIntent(pendingIntent);
//
//        // Set the notification to auto-cancel. This means that the notification will disappear
//        // after the user taps it, rather than remaining until it's explicitly dismissed.
//        builder.setAutoCancel(true);
//
//        /**
//         *Build the notification's appearance.
//         * Set the large icon, which appears on the left of the notification. In this
//         * sample we'll set the large icon to be the same as our app icon. The app icon is a
//         * reasonable default if you don't have anything more compelling to use as an icon.
//         */
//        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.checkhov));
//
//        /**
//         * Set the text of the notification. This sample sets the three most commononly used
//         * text areas:
//         * 1. The content title, which appears in large type at the top of the notification
//         * 2. The content text, which appears in smaller text below the title
//         * 3. The subtext, which appears under the text on newer devices. Devices running
//         *    versions of Android prior to 4.2 will ignore this field, so don't use it for
//         *    anything vital!
//         */
//        builder.setContentTitle("BasicNotifications Sample");
//        builder.setContentText("Time to learn about notifications!");
//        builder.setSubText("Tap to view documentation about notifications.");
//
//        // END_INCLUDE (build_notification)
//
//        // BEGIN_INCLUDE(send_notification)
//        /**
//         * Send the notification. This will immediately display the notification icon in the
//         * notification bar.
//         */
//        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(
//                Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(NOTIFICATION_ID, builder.build());
//        // END_INCLUDE(send_notification)
//    }

}
