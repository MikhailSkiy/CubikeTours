package com.example.mikhail.cubike.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikhail on 23.08.2015.
 */
public class CityManager {

    private static String[] cityArray_ = {"Taganrog","Rostov","Novocherkassk"};
    private static int[] tracks_ = {4,7,3};
    private static int[] points_ = {34,45,23};

    private static CityManager instance_;
    private List<City> cities_;

    public static CityManager getInstance(){
        if (instance_ == null){
            instance_ = new CityManager();
        }

        return instance_;
    }

    public List<City> getCities(){
        if (cities_ == null){
            cities_ = new ArrayList<City>();

            for (int i=0;i<cityArray_.length;i++){
                City city = new City();
                city.setName(cityArray_[i]);
                Log.v("City", cityArray_[i]);
                city.setTracksCount(tracks_[i]);
                Log.v("City", Integer.toString(tracks_[i]));
                city.setPointsCount(points_[i]);
                Log.v("City", Integer.toString(points_[i]));
                city.setCityId(1);
                city.setDescription("Lala");
                cities_.add(city);
            }

        }
        return cities_;
    }
}
