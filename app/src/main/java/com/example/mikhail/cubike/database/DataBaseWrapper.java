package com.example.mikhail.cubike.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.example.mikhail.cubike.database.DatabaseContract.*;

/**
 * Created by Mikhail Valuyskiy on 21.09.2015.
 */
public class DataBaseWrapper extends SQLiteOpenHelper
{
    private static String TAG = DataBaseWrapper.class.getName();
    private static String DB_PATH = "/data/data/com.example.mikhail.cubike/databases/";
    private static String DB_NAME = "new_db.db";
    private SQLiteDatabase myDataBase = null;
    private final Context myContext;

    public DataBaseWrapper(Context context)
    {
        super(context, DB_NAME, null, 1);

        this.myContext = context;
        DB_PATH="/data/data/" + context.getPackageName() + "/" + "databases/";
        Log.v("log_tag", "DBPath: " + DB_PATH);
        //  File f=getDatabasePath(DB_NAME);
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if(dbExist){
            Log.v("log_tag", "database does exist");
        }else{
            Log.v("log_tag", "database does not exist");
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private void copyDataBase() throws IOException{
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    private boolean checkDataBase(){

        File dbFile = new File(DB_PATH + DB_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();

    }

    public boolean openDataBase() throws SQLException
    {
        String mPath = DB_PATH + DB_NAME;
        //Log.v("mPath", mPath);
        myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return myDataBase != null;

    }


    @Override
    public synchronized void close()
    {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
//        final String SQL_CREATE_CITIES_TABLE = "CREATE TABLE " + CitiesTable.TABLE_NAME + " (" +
//                CitiesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                CitiesTable.CITY_NAME + " TEXT NOT NULL, " +
//                CitiesTable.CITY_DESCRIPTION + " TEXT NOT NULL, " +
//                CitiesTable.TRACKS_COUNT + " INTEGER NOT NULL, " +
//                CitiesTable.POINTS_COUNT + " INTEGER NOT NULL, " +
//                CitiesTable.CITY_COVER + " BLOB );";
//
//        final String SQL_CREATE_TRACKS_TABLE = "CREATE TABLE " + TracksTable.TABLE_NAME + " (" +
//                TracksTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                TracksTable.TRACK_TITLE + " TEXT NOT NULL, " +
//                TracksTable.DESCRIPTION + " TEXT NOT NULL, " +
//                TracksTable.DURATION + " INTEGER NOT NULL, " +
//                TracksTable.LENGTH + " INTEGER NOT NULL, " +
//                TracksTable.RATING + " INTEGER, " +
//                TracksTable.ICON_ID + " TEXT, " +
//                TracksTable.ICON + " BLOB );";
//
//        final String SQL_CREATE_PLACES_TABLE = "CREATE TABLE " + PlacesTable.TABLE_NAME + " (" +
//                PlacesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                PlacesTable.PLACE_TITLE + " TEXT NOT NULL, " +
//                PlacesTable.PLACE_SHORT_DESCRIPTION + " TEXT NOT NULL, " +
//                PlacesTable.PLACE_FULL_DESCRIPTION + " TEXT, " +
//                PlacesTable.LATITUDE + " REAL NOT NULL, " +
//                PlacesTable.LONGITUDE + " REAL NOT NULL, " +
//                PlacesTable.ICON_ID + " TEXT, " +
//                PlacesTable.TRACK_ID + " INTEGER NOT NULL, " +
//                PlacesTable.PLACE_ICON + " BLOB );";
//
//        db.execSQL(SQL_CREATE_TRACKS_TABLE);
//        db.execSQL(SQL_CREATE_PLACES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.v(TAG, "Upgrading database, this will drop database and recreate.");
    }
}