package sodevan.sarcar2.Service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sodevan.sarcar2.Models.NearbyStreets;

/**
 * Created by kartiksharma on 12/04/17.
 */

public interface NSservice {
    @GET("findNearbyStreetsOSMJSON?")
    Call<NearbyStreets> getstreets(@Query("lat") String lat,@Query("lng") String lng,@Query("username") String user);
}
