package com.lockers.doorknocking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2;
    TextView tv;
    Button b1;
    Intent in;


    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static MainActivity mainActivity = null;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = this;
        setContentView(R.layout.activity_main);

        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);

        b1=(Button)findViewById(R.id.button);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);



        //http://192.168.43.2:5000/
        tv = (TextView) findViewById(R.id.test);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestManager requestManager = RequestManager.getRequestManager();
                requestManager.authRequest(getApplicationContext(), ed1, ed2, mainActivity, tv);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        if(sharedpreferences.getString(Name,null)!=null){
            in = new Intent(MainActivity.this,log_Activity.class);
            startActivity(in);
        }
    }

    public void correctAuthentication(){
        String n  = ed1.getText().toString();
        String ph  = ed2.getText().toString();

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.putString(Phone, ph);
        editor.commit();

        in = new Intent(MainActivity.this,log_Activity.class);
        startActivity(in);
    }
}
