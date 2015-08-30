package com.example.mikhail.cubike.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import activities.PlaceListFragment;
import com.example.mikhail.cubike.R;
import activities.TrackMapFragment;

/**
 * Created by Mikhail on 04.08.2015.
 */
public class FragmentPagerCustomAdapter extends FragmentPagerAdapter{

    private final int PAGE_COUNT = 2;
    private Context context_;
    private int selectedTrackId_;

    public FragmentPagerCustomAdapter(Context context,FragmentManager fragmentManager, int Id){
        super(fragmentManager);
        this.context_ = context;
        selectedTrackId_ = Id;
    }

    public Fragment getItem(int arg0) {
        Bundle data = new Bundle();
        switch(arg0){
            /** Android tab is selected */
            case 0:
               PlaceListFragment androidFragment = new PlaceListFragment();
                data.putInt("current_page", arg0+1);
                data.putInt("Id",selectedTrackId_);
                androidFragment.setArguments(data);
                return androidFragment;
          //  return PlaceListFragment.newInstance(0);

            /** Apple tab is selected */
            case 1:
                TrackMapFragment appleFragment = new TrackMapFragment();
                data.putInt("current_page", arg0+1);
                data.putInt("Id",selectedTrackId_);
                appleFragment.setArguments(data);
                return appleFragment;
        }
        return null;
    }

    /** Returns the number of pages */
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            /** Android tab is selected */
            case 0:
                title =  context_.getResources().getString(R.string.view_pager_title_list);
                break;
            case 1:
                title = context_.getResources().getString(R.string.view_pager_title_map);
                break;
            default:
                break;
        }
        return title;
    }


}
