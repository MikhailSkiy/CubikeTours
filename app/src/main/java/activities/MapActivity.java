package activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ListView;

import com.example.mikhail.cubike.R;
import com.example.mikhail.cubike.adapters.FragmentPagerCustomAdapter;
import com.example.mikhail.cubike.database.DatabaseHelper;
import com.example.mikhail.cubike.interfaces.UIactions;
import com.example.mikhail.cubike.managers.RequestManager;
import com.example.mikhail.cubike.model.Place;
import com.example.mikhail.cubike.model.Preview;
import com.example.mikhail.cubike.service.LocationService;
import com.google.android.gms.maps.GoogleMap;

import java.util.List;


public class MapActivity extends FragmentActivity implements UIactions {

    private GoogleMap map_; // Might be null if Google Play services APK is not available.
    private ListView listView_;
    private RequestManager manager_ = new RequestManager(this);
    ViewPager vpPager;
    List<Preview> places_;
    int selectedTrackId;
    FragmentPagerAdapter adapterViewPager;
    static Context context_;
   // private DatabaseHelper helper_ = new DatabaseHelper(this);
    private List<Place> foundPlaces_;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
         context_ = getApplicationContext();

         // Start service
         //this.startService(new Intent(this, LocationService.class));

       //  helper_.getReadableDatabase();

        // Gets selected track id
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        selectedTrackId = extras.getInt("SelectedItemId", 0);

         Bundle bundle = new Bundle();

         bundle.putInt("Id",selectedTrackId );

         vpPager = (ViewPager) findViewById(R.id.pager);
         adapterViewPager = new FragmentPagerCustomAdapter(this,getSupportFragmentManager(),selectedTrackId);
         vpPager.setAdapter(adapterViewPager);

         vpPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                 Log.v("Page","onPageScrolled");

             }

             @Override
             public void onPageSelected(int position) {
                 Log.v("Page","onPageSelected");
                 updateCurrentFragment();
             }

             @Override
             public void onPageScrollStateChanged(int state) {
                 Log.v("Page","onPageScrollStateChanged");
             }
         });
    }

    @Override
    public Context getClientActivityContext() {
        return context_;
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    public void showPlaces(int currentItemIndex, List<Preview> placesPreviews){
        FragmentPagerCustomAdapter adapter = ((FragmentPagerCustomAdapter)vpPager.getAdapter());
        Fragment currentFragment = (Fragment) adapter.instantiateItem(vpPager,currentItemIndex);
        if ( currentFragment instanceof PlaceListFragment){
            Log.v("IN"," PlaceListFragment");
            PlaceListFragment placeListFragment = (PlaceListFragment) currentFragment;
            placeListFragment.updateUI(placesPreviews);
        }

        if (currentFragment instanceof TrackMapFragment) {
            Log.v("IN","TrackMapFragment");
            TrackMapFragment trackMapFragment = (TrackMapFragment) currentFragment;
            trackMapFragment.updateUI(placesPreviews);
        }
    }

    private void updateCurrentFragment(){
        int index = vpPager.getCurrentItem();
        FragmentPagerCustomAdapter adapter = ((FragmentPagerCustomAdapter)vpPager.getAdapter());

        Fragment currentFragment = (Fragment) adapter.instantiateItem(vpPager,index);
        if ( currentFragment instanceof PlaceListFragment){
            Log.v("IN"," PlaceListFragmen");
            currentFragment = (PlaceListFragment)adapter.instantiateItem(vpPager,index);
            ((PlaceListFragment) currentFragment).updateUI(places_);
        }

        if (currentFragment instanceof TrackMapFragment) {
            Log.v("IN","TrackMapFragment");
            ((TrackMapFragment)currentFragment).updateUI(places_);
        }
    }

    public void updateUI(List<Preview> placesPreviews) {
        places_ = placesPreviews;
        Log.v("Places",Integer.toString(placesPreviews.size()));
        updateCurrentFragment();



     //   PlaceListFragment placeListFragment = (PlaceListFragment) adapter.instantiateItem(vpPager,index);


        Context context = this;
        if (context!=null){
            Log.v("ACOntext","Not null");
        } else  Log.v("ACOntext","null");

      //  placeListFragment.updateUI(placesPreviews);
    }

    //  manager_.sendAsyncPlaceRequest(selectedTrackId);


//        List<Place> places= new ArrayList<Place>();
//
//        listView_ = (ListView) this.findViewById(R.id.places_list);
//
//        listView_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                switch (position) {
//                    case 0:
//                        map_.addMarker(new MarkerOptions().position(
//                                new LatLng(47.2096054, 38.9352142)).icon(
//                                BitmapDescriptorFactory.defaultMarker()));
//                        CameraUpdate center1 = CameraUpdateFactory.newLatLng(new LatLng(47.2096054, 38.9352142));
//                        CameraUpdate zoom1 = CameraUpdateFactory.zoomTo(16);
//                        map_.moveCamera(center1);
//                        map_.animateCamera(zoom1);
//
//                        break;
//                    case 1:
//                        map_.addMarker(new MarkerOptions().position(
//                                new LatLng(47.2105, 38.9338999)).icon(
//                                BitmapDescriptorFactory.defaultMarker()));
//                        CameraUpdate center2 = CameraUpdateFactory.newLatLng(new LatLng(47.2105, 38.9338999));
//                        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
//
//
//                        map_.moveCamera(center2);
//                        map_.animateCamera(zoom);
//                        break;
//                }
//            }
//        });

}
