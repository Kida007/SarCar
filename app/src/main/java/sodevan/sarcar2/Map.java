package sodevan.sarcar2;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import sodevan.sarcar2.Service.CarObject;

public class Map extends AppCompatActivity implements OnMapReadyCallback {
    final Context context = this;
    GoogleMap gmap ;
    Marker marker;
    private int flag = 0  ;
    TextView loc_road  ,  speedtv;

    Firebase_datalayer fb=new Firebase_datalayer();
    Location mLocation ;
    String road="";
    ArrayList<String> roads  ;
    ArrayList<String> j ;
    String prev_road="";
    double prev_lat=0,prev_long=0;

    TextToSpeech t1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        speedtv = (TextView)findViewById(R.id.speedtv) ;
        loc_road = (TextView)findViewById(R.id.loc_road) ;



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Roads");
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()) ;
        final String uname=sp.getString("vehicleno","0000");
        final FusedLocation fusedLocation = new FusedLocation(context, new FusedLocation.Callback(){
            @Override
            public void onLocationResult(Location location){
                //Do as you wish with location here
                mLocation = location ;

                 Float speed = location.getSpeed();
                 speed =  (float)Math.round(speed*100)/100 ;
                speedtv.setText(speed+" m/s");

                Log.d("TAG","loc:"+location.getLatitude()+" long : "+location.getLongitude() + "direction : "+location.getBearing());


                Double latitude = location.getLatitude() ;
                Double longitude = location.getLongitude() ;
                float Bearing = location.getBearing() ;

                if (latitude!=null || longitude!=null ){
                    if(!road.equals("")){
                        loc_road.setText(road);
                        prev_road=road;
                    }

                        fb.getRoad(latitude,longitude ,getApplicationContext());
                        String rol = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("roads", null) ;
                        if(rol!=null) {
                            Gson gson = new Gson();
                            Type type = new TypeToken<ArrayList<String>>() {}.getType();
                            roads = gson.fromJson(rol, type);
                            Log.i("jjkk2" , roads+"") ;
                            road= roads.get(0) ;
                            ArrayList<String> j = roads ;
                            j.remove(0) ;
                            myRef.child(prev_road).child(uname).removeValue();


                            HashMap<String , HashMap<String , String>> hp = getnextlatlong(new LatLng(location.getLatitude() , location.getLongitude()) , speed , Bearing) ;
                            Log.i("nextLatLong" , hp+" ") ;

                            CarObject co = new CarObject(location.getLatitude()+"", location.getLongitude()+"" , prev_lat+"" , prev_long+"" , Bearing+"" , hp  , j) ;
                            myRef.child(road).child(uname).setValue(co) ;
                            prev_lat=latitude;
                            prev_long=longitude;
                        }


                    updateloc(new LatLng(location.getLatitude() , location.getLongitude()));
                }


            }
        });



        TimerTask t=new TimerTask() {
            @Override
            public void run() {
                fusedLocation.getCurrentLocation(3);

            }
        };

        Timer time=new Timer();
        time.schedule(t,0,5000);

    }





    public void animateMarker (final Marker marker, final LatLng topostion , final boolean hideMarker) {
        final Handler handler = new Handler() ;

        final long start = SystemClock.uptimeMillis() ;
        Projection projection = gmap.getProjection() ;

        // Getting current Marker Location
        Point startpoint = projection.toScreenLocation(marker.getPosition()) ;
        final LatLng startLatlong = projection.fromScreenLocation(startpoint) ;
        final long duration = 500 ;

        final LinearInterpolator interpolator = new LinearInterpolator() ;

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis()-start ;

                float t = interpolator.getInterpolation((float)elapsed/duration) ;
                double lng = t * topostion.longitude + (1 - t)
                        * startLatlong.longitude;
                double lat = t * topostion.latitude + (1 - t)
                        * startLatlong.latitude;

                marker.setPosition(new LatLng(lat , lng));

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                }

                else {
                    if (hideMarker) {
                        marker.setVisible(false);
                    } else {
                        marker.setVisible(true);
                    }

                }



            }
        });

    }



    public void updateloc(LatLng loc) {
        Bitmap bm = BitmapFactory.decodeResource(getResources() , R.drawable.car) ;
        Bitmap im = Bitmap.createScaledBitmap(bm , 80 , 179 , false) ;
        MarkerOptions markerop=  new MarkerOptions().position(loc).icon(BitmapDescriptorFactory.fromBitmap(im)) ;

        if (flag==0 && gmap!=null) {
            marker = gmap.addMarker(markerop) ;
            gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
            flag=1 ;
        }

        else if (marker!=null){
            animateMarker(marker , loc , false);
            gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));

        }
    }

    // Curve Prediction Model Function
    public HashMap<String , HashMap<String , String>> getnextlatlong( LatLng ln , Float speed , float Bearing )  {

        HashMap< String , HashMap<String , String >> arll = new HashMap<>() ;
        speed = speed * 3.6f  ;

        int Initial_time = getCurrentTime() ;
        for( int i=1 ; i<=100 ; i++)   {

            double R = 6378.1   ;
            Log.i("Dspeed" , speed+"") ;

            double distance_travelled = speed * 0.0000416667 *i ;
            double bearing_angle = Bearing ;
            double lat1 = Math.toRadians(ln.latitude) ;
            double lon1 = Math.toRadians(ln.longitude) ;

            double lat2 = Math.asin( Math.sin(lat1)*Math.cos(distance_travelled/R) + Math.cos(lat1)*Math.sin(distance_travelled/R)*Math.cos(bearing_angle) );
            double a = Math.atan2(Math.sin(bearing_angle)*Math.sin(distance_travelled/R)*Math.cos(lat1), Math.cos(distance_travelled/R)-Math.sin(lat1)*Math.sin(lat2));
            double lon2 = lon1 + a;

            int time  = Initial_time + i*150 ;



            //Converting Back  to from Radians Degrees

            lat2 = Math.toDegrees(lat2) ;
            lon2 = Math.toDegrees(lon2);
            HashMap<String , String> latlong = new HashMap<>() ;
            latlong.put("lat" , lat2+"") ;
            latlong.put("lon" , lon2+"") ;




            arll.put(time+"" , latlong) ;

        }

        return arll ;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        gmap=googleMap ;

    }



    public int getCurrentTime (){

        int time = (int) (System.currentTimeMillis());
        return time ;
    }





}