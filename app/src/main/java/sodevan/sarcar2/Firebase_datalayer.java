package sodevan.sarcar2;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sodevan.sarcar2.Models.NearbyStreets;
import sodevan.sarcar2.Service.NSservice;

/**
 * Created by ronaksakhuja on 13/04/17.
 */

public class Firebase_datalayer {
    String uname;
//    Firebase_datalayer(String uname){
//        this.uname=uname;
//    }

    String res="no road";



    public String getRoad(double lat,double lon){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.geonames.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NSservice apiservice = retrofit.create(NSservice.class);

        Call<NearbyStreets> call=apiservice.getstreets(String.valueOf(lat),String.valueOf(lon),"kartik_sharma");
        call.enqueue(new Callback<NearbyStreets>() {
            @Override
            public void onResponse(Call<NearbyStreets> call, Response<NearbyStreets> response) {
                Log.d("TAG",response.body().getStreetSegment().get(0).getName());
                res= response.body().getStreetSegment().get(0).getName();

            }

            @Override
            public void onFailure(Call<NearbyStreets> call, Throwable t) {

            }
        });
        return res;
    }

}
