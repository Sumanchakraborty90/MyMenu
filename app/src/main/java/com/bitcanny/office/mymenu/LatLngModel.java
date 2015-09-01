package com.bitcanny.office.mymenu;

/**
 * Created by OFFICE on 03-08-2015.
 */
public class LatLngModel  {

    int latlngid;
    String latitude;
    String longitude;
    String resturant_Id;


    public LatLngModel() {
    }

    public LatLngModel( String latitude, String longitude, String resturant_Id) {

        this.latitude = latitude;
        this.longitude = longitude;
        this.resturant_Id = resturant_Id;
    }

    public int getLatlngid() {
        return latlngid;
    }

    public void setLatlngid(int latlngid) {
        this.latlngid = latlngid;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getResturant_Id() {
        return resturant_Id;
    }

    public void setResturant_Id(String resturant_Id) {
        this.resturant_Id = resturant_Id;
    }
}
