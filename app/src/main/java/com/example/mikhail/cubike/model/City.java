package com.example.mikhail.cubike.model;

/**
 * Created by Mikhail on 23.08.2015.
 */

import android.content.Context;

/**
 * Represents the city
 */
public class City {
    private int cityId_;
    private String name_;
    private String description_;
    private int tracksCount_;
    private int pointsCount_;
    private byte[] icon_;


    public int getCityId() {
        return cityId_;
    }

    public void setCityId(int cityId) {
        this.cityId_ = cityId;
    }

    public String getName() {
        return name_;
    }

    public void setName(String name) {
        this.name_ = name;
    }

    public String getDescription() {
        return description_;
    }

    public void setDescription(String description) {
        this.description_ = description;
    }

    public int getTracksCount() {
        return tracksCount_;
    }

    public void setTracksCount(int tracksCount) {
        this.tracksCount_ = tracksCount;
    }

    public int getPointsCount() {
        return pointsCount_;
    }

    public void setPointsCount(int pointsCount) {
        this.pointsCount_ = pointsCount;
    }

    public byte[] getIcon() {
        return icon_;
    }

    public void setIcon(byte[] icon) {
        this.icon_ = icon;
    }

    public int getImageResourceId(Context context)
    {
        try {
            return context.getResources().getIdentifier(this.name_, "drawable", context.getPackageName());

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
