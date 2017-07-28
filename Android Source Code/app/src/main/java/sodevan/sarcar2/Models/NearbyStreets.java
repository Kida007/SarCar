
package sodevan.sarcar2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NearbyStreets {

    @SerializedName("streetSegment")
    @Expose
    private List<StreetSegment> streetSegment = null;

    public List<StreetSegment> getStreetSegment() {
        return streetSegment;
    }

    public void setStreetSegment(List<StreetSegment> streetSegment) {
        this.streetSegment = streetSegment;
    }

}
