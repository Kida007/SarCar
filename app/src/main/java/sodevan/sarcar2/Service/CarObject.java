package sodevan.sarcar2.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ravipiyush on 13/04/17.
 */

public class CarObject {
    String lat ;
    String lon ;
    String prev_lat ;
    String  prev_lon ;
    String  bearing ;
    HashMap<String , HashMap<String ,String> > nextlatlon ;
    ArrayList<String> near_by_segments ;


    public CarObject() {
    }

    public CarObject(String lat, String lon, String prev_lat, String prev_lon, String bearing, HashMap<String, HashMap<String, String>> nextlatlon, ArrayList<String> near_by_segments) {
        this.lat = lat;
        this.lon = lon;
        this.prev_lat = prev_lat;
        this.prev_lon = prev_lon;
        this.bearing = bearing;
        this.nextlatlon = nextlatlon;
        this.near_by_segments = near_by_segments;
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

    public String getBearing() {
        return bearing;
    }

    public void setBearing(String bearing) {
        this.bearing = bearing;
    }

    public HashMap<String, HashMap<String, String>> getNextlatlon() {
        return nextlatlon;
    }

    public void setNextlatlon(HashMap<String, HashMap<String, String>> nextlatlon) {
        this.nextlatlon = nextlatlon;
    }

    public ArrayList<String> getNear_by_segments() {
        return near_by_segments;
    }

    public void setNear_by_segments(ArrayList<String> near_by_segments) {
        this.near_by_segments = near_by_segments;
    }
}
