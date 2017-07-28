
package sodevan.sarcar2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StreetSegment {

    @SerializedName("wayId")
    @Expose
    private String wayId;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("line")
    @Expose
    private String line;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("highway")
    @Expose
    private String highway;
    @SerializedName("oneway")
    @Expose
    private String oneway;

    public String getWayId() {
        return wayId;
    }

    public void setWayId(String wayId) {
        this.wayId = wayId;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHighway() {
        return highway;
    }

    public void setHighway(String highway) {
        this.highway = highway;
    }

    public String getOneway() {
        return oneway;
    }

    public void setOneway(String oneway) {
        this.oneway = oneway;
    }

}
