package com.example.mikhail.cubike.model;

/**
 * Created by Mikhail on 21.07.2015.
 */
public class Place {
    private int id_;
    private String title_;
    private String shortDescription_;
    private String fullDescription_;
    private double latitude_;
    private double longitude_;
    private int trackId_;

    private byte[] icon_;

    public Place(int id_, String title_, String shortDescription_, String fullDescription_, double latitude_, double longitude_, byte[] icon_, int trackId_) {
        this.id_ = id_;
        this.title_ = title_;
        this.shortDescription_ = shortDescription_;
        this.fullDescription_ = fullDescription_;
        this.latitude_ = latitude_;
        this.longitude_ = longitude_;
        this.icon_ = icon_;
        this.trackId_ = trackId_;
    }

    public Place(String title,String description){
        this.title_ = title;
        this.shortDescription_ = description;
    }

    public void setTitle(String title){
        this.title_ = title;
    }

    public String getTitle(){
        return this.title_;
    }

    public void setShortDescription(String description){
        this.shortDescription_ = description;
    }

    public String getShortDescription(){
        return this.shortDescription_;
    }

    public void setFullDescription(String description){
        this.fullDescription_ = description;
    }

    public String getFullDescription(){
        return this.fullDescription_;
    }

    public double getLatitude() {
        return latitude_;
    }

    public void setLatitude(double latitude_) {
        this.latitude_ = latitude_;
    }


    public double getLongitude() {
        return longitude_;
    }

    public void setLongitude(double longitude_) {
        this.longitude_ = longitude_;
    }

    public byte[] getIcon() {
        return icon_;
    }

    public void setIcon(byte[] icon) {
        this.icon_ = icon;
    }

    public void setTrackId(int trackId){
        this.trackId_ = trackId;
    }

    public int getTrackId(){
        return this.trackId_;
    }
}
