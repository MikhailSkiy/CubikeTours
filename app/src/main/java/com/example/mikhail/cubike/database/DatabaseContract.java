package com.example.mikhail.cubike.database;

import android.provider.BaseColumns;

/**
 * Created by Mikhail on 29.07.2015.
 */
public class DatabaseContract {

    /**
     * Class represents all fields of City Table
     */
    public static final class CitiesTable implements BaseColumns{
        public static final String TABLE_NAME = "cities";
        public static final String CITY_NAME = "name";
        public static final String CITY_DESCRIPTION = "description";
        public static final String TRACKS_COUNT = "tracks_count";
        public static final String POINTS_COUNT = "points_count";
        public static final String CITY_COVER = "city_cover";
        public static final String CITY_ID = "city_id";
    }

    /**
     * Class represents all fields of Tracks Table
     */
    public static final class TracksTable implements BaseColumns{
        public static final String TABLE_NAME = "tracks";
        public static final String TRACK_TITLE = "title";
        public static final String DURATION = "duration";
        public static final String DESCRIPTION = "description";
        public static final String LENGTH = "length";
        public static final String RATING = "rating";
        public static final String ICON = "icon";
        public static final String COMPLEXITY = "complexity";

    }

    /**
     * Class represents all fields of Places Table
     */
    public static final class PlacesTable implements BaseColumns{
        public static final String TABLE_NAME = "places";
        public static final String PLACE_TITLE = "title";
        public static final String PLACE_SHORT_DESCRIPTION = "short_description";
        public static final String PLACE_FULL_DESCRIPTION = "full_description";
        public static final String PLACE_ICON = "icon";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String TRACK_ID = "track_id";
    }

}
