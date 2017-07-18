package sodevan.sarcar2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText et1,et2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this) ;
        String vi = sp.getString("vehicleno", "no") ;
        if (!vi.equals("no")){

            Intent changeintent = new Intent(this , Map.class) ;
            startActivity(changeintent);
            finish();
        }


         et1 = (EditText)findViewById(R.id.vehicleno) ;

         et2 = (EditText)findViewById(R.id.mobileno) ;

        Button btn  = (Button)findViewById(R.id.submitbutton)  ;




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String vehicleno = et1.getText().toString() ;
                String mobileno = et2.getText().toString() ;
                if(vehicleno.equals("")||mobileno.equals("")){
                    Toast.makeText(Login.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                }

                else{

                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()) ;
                    sp.edit().putString("vehicleno" , vehicleno).putString("mobileno" , mobileno).apply();

                    Intent changeintent = new Intent(getApplicationContext() , Map.class) ;
                    startActivity(changeintent);
                    finish();
                }

            }
        });



    }
}
