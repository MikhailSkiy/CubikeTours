package com.example.mikhail.cubike.model;

/**
 * Created by Mikhail on 19.07.2015.
 */
public class Track {

    private int trackId_;
    private String title_;
    private String description_;
    // The duration of track in min
    private int duration_;
    // The length of track in km
    private int length_;
    // The rating from 1 to 5
    private int rating_;
    // The complexity of track (from 1(easy) to 3 (hard))
    private int complexity_;
    private int cityId_;
    // Icon for track
    private byte[] icon_;

    public Track(int id, String title, String description, int duration, int length, int rating, int complexity, int cityId, byte[] icon_) {
        this.trackId_ = id;
        this.title_ = title;
        this.description_ = description;
        this.duration_ = duration;
        this.length_ = length;
        this.rating_ = rating;
        this.complexity_ = complexity;
        this.cityId_ = cityId;
        this.icon_ = icon_;
    }

    public Track(int id, String title, String description, int duration, int length, int rating, int complexity, int cityId) {
        this.trackId_ = id;
        this.title_ = title;
        this.description_ = description;
        this.duration_ = duration;
        this.length_ = length;
        this.rating_ = rating;
        this.complexity_ = complexity;
        this.cityId_ = cityId;
    }

    public Track(int id, String title, String description, int duration, int length, int rating, byte[] icon) {
        this.trackId_ = id;
        this.title_ = title;
        this.description_ = description;
        this.duration_ = duration;
        this.length_ = length;
        this.rating_ = rating;
        this.icon_ = icon;
    }

    public int getTrackId() {
        return trackId_;
    }

    public void setTrackId(int trackId_) {
        this.trackId_ = trackId_;
    }


    public String getTitle() {
        return title_;
    }

    public void setTitle(String title) {
        this.title_ = title;
    }

    public String getDescription() {
        return description_;
    }

    public void setDescription(String description) {
        this.description_ = description;
    }

    public int getDuration() {
        return duration_;
    }

    public void setDuration(int duration) {
        this.duration_ = duration;
    }

    public int getLength() {
        return length_;
    }

    public void setLength(int length) {
        this.length_ = length;
    }

    public int getRating() {
        return rating_;
    }

    public void setRating(int rating) {
        this.rating_ = rating;
    }

    public byte[] getIcon() {
        return icon_;
    }

    public void setIcon_(byte[] icon_) {
        this.icon_ = icon_;
    }

}
