package com.example.mikhail.cubike.model;

/**
 * Created by Mikhail on 02.08.2015.
 */
public class Preview {

    private int id_;
    private String title_;
    private String description_;
    private int duration_;
    private int length_;
    private byte[] icon_;
    private double latitude_;
    private double longitude_;


    public Preview(String title_, String description_) {
        this.title_ = title_;
        this.description_ = description_;
    }

    public Preview(int  id_,String title_, String description_, int duration_, int length_, byte[] icon_) {
        this.id_ = id_;
        this.title_ = title_;
        this.description_ = description_;
        this.duration_ = duration_;
        this.length_ = length_;
        this.icon_ = icon_;
    }

    public Preview(int  id_,String title_, String description_, int duration_, int length__) {
        this.id_ = id_;
        this.title_ = title_;
        this.description_ = description_;
        this.duration_ = duration_;
        this.length_ = length_;
    }

    public Preview(){}


    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public int getLength_() {
        return length_;
    }

    public void setLength_(int length_) {
        this.length_ = length_;
    }

    public int getDuration_() {
        return duration_;
    }

    public void setDuration_(int duration_) {
        this.duration_ = duration_;
    }

    public String getDescription_() {
        return description_;
    }

    public void setDescription_(String description_) {
        this.description_ = description_;
    }

    public String getTitle_() {
        return title_;
    }

    public void setTitle_(String title_) {
        this.title_ = title_;
    }

    public byte[] getIcon_() {
        return icon_;
    }

    public void setIcon_(byte[] icon_) {
        this.icon_ = icon_;
    }

    public double getLatitude(){
        return this.latitude_;
    }

    public void setLatitude(double latitude){
        this.latitude_ = latitude;
    }

    public double getLongitude(){
        return this.longitude_;
    }

    public void setLongitude(double longitude){
        this.longitude_ = longitude;
    }


}
