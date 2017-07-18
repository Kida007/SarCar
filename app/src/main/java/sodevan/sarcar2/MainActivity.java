package sodevan.sarcar2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int Splashtime=3000 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView logotext = (TextView)findViewById(R.id.logotext) ;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent changeactivity  = new Intent(getApplicationContext() , Login.class) ;
                startActivity(changeactivity) ;
                finish();

            }
        } , Splashtime) ;
    }
}
