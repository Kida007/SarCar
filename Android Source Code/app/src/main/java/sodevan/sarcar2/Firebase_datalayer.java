package sodevan.sarcar2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

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

    ArrayList<String> roads ;


    public void getRoad(double lat, double lon , final Context c1){


        roads = new ArrayList<>() ;
        roads.add(0 ,"no road");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.geonames.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NSservice apiservice = retrofit.create(NSservice.class);

        Call<NearbyStreets> call=apiservice.getstreets(String.valueOf(lat),String.valueOf(lon),"kartik_sharma");
        call.enqueue(new Callback<NearbyStreets>() {
            @Override
            public void onResponse(Call<NearbyStreets> call, Response<NearbyStreets> response) {
                List<sodevan.sarcar2.Models.StreetSegment> o1 = response.body().getStreetSegment() ;
                Log.i("jjkk" , o1+"");

                if(o1 !=null) {
                    roads = new ArrayList<>();
                    for (sodevan.sarcar2.Models.StreetSegment c1 : o1) {
                        roads.add(c1.getName());

                    }

                    Gson gson = new Gson();
                    String json = gson.toJson(roads);
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c1);
                    sp.edit().putString("roads", json).commit();

                }
            }

            @Override
            public void onFailure(Call<NearbyStreets> call, Throwable t) {

            }
        });
    }

}
