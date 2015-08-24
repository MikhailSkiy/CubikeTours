package com.example.mikhail.cubike.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mikhail.cubike.model.Place;
import com.example.mikhail.cubike.model.Track;

import java.util.ArrayList;
import java.util.List;

import static com.example.mikhail.cubike.database.DatabaseContract.PlacesTable;
import static com.example.mikhail.cubike.database.DatabaseContract.TracksTable;

/**
 * Created by Mikhail on 01.08.2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // When the database schema was changed, you must increment the database version
    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "cubike.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates table to hold track and place data
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        final String SQL_CREATE_TRACKS_TABLE = "CREATE TABLE " + TracksTable.TABLE_NAME + " (" +
                TracksTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TracksTable.TRACK_TITLE + " TEXT NOT NULL, " +
                TracksTable.DESCRIPTION + " TEXT NOT NULL, " +
                TracksTable.DURATION + " INTEGER NOT NULL, " +
                TracksTable.LENGTH + " INTEGER NOT NULL, " +
                TracksTable.RATING + " INTEGER, " +
                TracksTable.ICON + " BLOB );";

        final String SQL_CREATE_PLACES_TABLE = "CREATE TABLE " + PlacesTable.TABLE_NAME + " (" +
                PlacesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PlacesTable.PLACE_TITLE + " TEXT NOT NULL, " +
                PlacesTable.PLACE_SHORT_DESCRIPTION + " TEXT NOT NULL, " +
                PlacesTable.PLACE_FULL_DESCRIPTION + " TEXT, " +
                PlacesTable.LATITUDE + " REAL NOT NULL, " +
                PlacesTable.LONGITUDE + " REAL NOT NULL, " +
                PlacesTable.TRACK_ID + " INTEGER NOT NULL, " +
                PlacesTable.PLACE_ICON + " BLOB );";

        sqLiteDatabase.execSQL(SQL_CREATE_TRACKS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PLACES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        onCreate(sqLiteDatabase);
    }

    //region Methods for working with places

    public void addAllPlaces(List<Place> places){
        for (int i=0;i<places.size();i++){
            addPlace(places.get(i));
        }
    }

    public void addPlace(Place place){
        if (place != null) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = putPlaceIntoContentValues(place);
            db.insert(PlacesTable.TABLE_NAME,null,values);
            db.close();
        } else {
            throw new IllegalArgumentException("Passed place object is null");
        }
    }

    public ContentValues putPlaceIntoContentValues(Place place){
        ContentValues values = new ContentValues();
        values.put(PlacesTable.PLACE_TITLE,place.getTitle());
        values.put(PlacesTable.PLACE_SHORT_DESCRIPTION,place.getShortDescription());
        values.put(PlacesTable.PLACE_FULL_DESCRIPTION,place.getFullDescription());
        values.put(PlacesTable.LATITUDE,place.getLatitude());
        values.put(PlacesTable.LONGITUDE,place.getLongitude());
        values.put(PlacesTable.PLACE_ICON,place.getIcon());
        values.put(PlacesTable.TRACK_ID,place.getTrackId());
        return values;
    }

    public Place getPlaceById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM " + PlacesTable.TABLE_NAME +
                " WHERE " + PlacesTable._ID + " = " + id;

        Cursor cursor = db.rawQuery(selectQuery,null);
        Place place;
        if (cursor.moveToFirst()){
            place = getPlaceFromCursor(cursor);
        } else {
            return null;
        }
        db.close();
        return place;
    }

    public List<Place> getPlacesByTrackId(int trackId){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Place> places = new ArrayList<Place>();
        String selectQuery = "SELECT * FROM " + PlacesTable.TABLE_NAME + " WHERE " + PlacesTable.TRACK_ID + " = " + Integer.toString(trackId);
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                Place place = getPlaceFromCursor(cursor);
                places.add(place);
            } while (cursor.moveToNext());
        }
        db.close();
        return places;
    }

    public List<Place> getAllPlaces(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Place> places = new ArrayList<Place>();
        String selectQuery = "SELECT * FROM " + PlacesTable.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                Place place = getPlaceFromCursor(cursor);
                places.add(place);
            } while (cursor.moveToNext());
        }
        db.close();
        return places;
    }

    private Place getPlaceFromCursor(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndex(PlacesTable._ID));
        String title = cursor.getString(cursor.getColumnIndex(PlacesTable.PLACE_TITLE));
        String shortDesc = cursor.getString(cursor.getColumnIndex(PlacesTable.PLACE_SHORT_DESCRIPTION));
        String fullDesc = cursor.getString(cursor.getColumnIndex(PlacesTable.PLACE_FULL_DESCRIPTION));
        Double latitude = cursor.getDouble(cursor.getColumnIndex(PlacesTable.LATITUDE));
        Double longitude = cursor.getDouble(cursor.getColumnIndex(PlacesTable.LONGITUDE));
        byte[] icon = cursor.getBlob(cursor.getColumnIndex(PlacesTable.PLACE_ICON));
        int trackId = cursor.getInt(cursor.getColumnIndex(PlacesTable.TRACK_ID));
        Place place = new Place(id,title,shortDesc,fullDesc,latitude,longitude,icon,trackId);

        return place;
    }

    //endregion

    //region Methods for working with tracks

    public void addAllTracks(List<Track> tracks){
        for (int i=0;i<tracks.size();i++){
            addTrack(tracks.get(i));
        }
    }
    public void addTrack(Track track){
        if (track != null) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = putTrackIntoContentValues(track);
            db.insert(TracksTable.TABLE_NAME,null,values);
            db.close();
        } else {
            throw new IllegalArgumentException("Passed track object is null");
        }
    }

    public ContentValues putTrackIntoContentValues(Track track){
        ContentValues values = new ContentValues();
        values.put(TracksTable._ID,track.getTrackId());
        values.put(TracksTable.DURATION,track.getDuration());
        values.put(TracksTable.LENGTH,track.getLength());
        values.put(TracksTable.DESCRIPTION,track.getDescription());
        values.put(TracksTable.RATING,track.getRating());
        values.put(TracksTable.TRACK_TITLE,track.getTitle());
        values.put(TracksTable.ICON, track.getIcon());

        return values;
    }

    public List<Track> getAllTracks(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Track> tracks = new ArrayList<Track>();
        String selectQuery = "SELECT * FROM " + TracksTable.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                Track track = getTrackFromCursor(cursor);
                tracks.add(track);
            } while (cursor.moveToNext());
        }
        db.close();
        return tracks;
    }

    private Track getTrackFromCursor(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndex(TracksTable._ID));
        int duration = cursor.getInt(cursor.getColumnIndex(TracksTable.DURATION));
        int length = cursor.getInt(cursor.getColumnIndex(TracksTable.LENGTH));
        String title = cursor.getString(cursor.getColumnIndex(TracksTable.TRACK_TITLE));
        String description = cursor.getString(cursor.getColumnIndex(TracksTable.DESCRIPTION));
        int rating = cursor.getInt(cursor.getColumnIndex(TracksTable.RATING));
        byte[] icon = cursor.getBlob(cursor.getColumnIndex(TracksTable.ICON));
        Track track = new Track(id,title,description,duration,length,rating,icon);

        return track;
    }


    //endregion



}
