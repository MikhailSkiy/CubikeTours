package activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mikhail.cubike.R;
import com.example.mikhail.cubike.model.Preview;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


public class TrackMapFragment extends Fragment {

    private GoogleMap map_;
    private SupportMapFragment fragment;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();
        fragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, fragment).commit();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume(){
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (map_ == null) {
            // Try to obtain the map from the SupportMapFragment.
            SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
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

    public void updateUI(List<Preview> places){
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(47.2092003, 38.9334364));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(14);
        map_.moveCamera(center);
        map_.animateCamera(zoom);
        for (int i=0;i<places.size();i++){
            map_.addMarker(new MarkerOptions().position(
                    new LatLng(places.get(i).getLatitude(), places.get(i).getLongitude())).icon(
                    BitmapDescriptorFactory.defaultMarker()));

            Log.v("Lat", Double.toString(places.get(i).getLatitude()));
            Log.v("Lng",Double.toString(places.get(i).getLongitude()));
        }
    }

}
