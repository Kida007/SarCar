package sodevan.sarcar2.Service;

/**
 * Created by ravipiyush on 13/04/17.
 */

public class CarObject {
    String lat ;
    String lon ;
    String prev_lat ;
    String  prev_lon ;


    public CarObject() {
    }

    public CarObject(String lat, String lon, String prev_lat, String prev_lon) {
        this.lat = lat;
        this.lon = lon;
        this.prev_lat = prev_lat;
        this.prev_lon = prev_lon;
    }


    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getPrev_lat() {
        return prev_lat;
    }

    public void setPrev_lat(String prev_lat) {
        this.prev_lat = prev_lat;
    }

    public String getPrev_lon() {
        return prev_lon;
    }

    public void setPrev_lon(String prev_lon) {
        this.prev_lon = prev_lon;
    }
}
