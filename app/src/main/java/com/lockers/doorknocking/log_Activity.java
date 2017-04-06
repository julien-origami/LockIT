package com.lockers.doorknocking;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class log_Activity extends AppCompatActivity {

    Button bu=null;
    TextView userID =null;
    SharedPreferences sharedpreferences;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_);

        sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        bu=(Button)findViewById(R.id.button2);
        userID = (TextView)findViewById(R.id.userID);
        spinner = (Spinner)findViewById(R.id.spinner);

        userID.setText("Bienvenue "+sharedpreferences.getString(MainActivity.Name,null));


        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(v);
            }
        });
    }

    //http://192.168.43.2:5000/

    public void logout(View view){
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        finish();
    }


}
