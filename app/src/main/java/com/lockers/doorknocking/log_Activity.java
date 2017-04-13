package com.lockers.doorknocking;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

public class log_Activity extends AppCompatActivity {

    Button bu=null;
    TextView userID =null;
    SharedPreferences sharedpreferences;
    Spinner spinner;
    Button door;
    public static HashMap<String, String> hmap = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_);

        sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        bu=(Button)findViewById(R.id.button2);
        userID = (TextView)findViewById(R.id.userID);
        spinner = (Spinner)findViewById(R.id.spinner);
        door = (Button)findViewById(R.id.door);

        userID.setText("Bienvenue "+sharedpreferences.getString(MainActivity.Name,null));

        RequestManager requestManager = RequestManager.getRequestManager();
        requestManager.getDoorsRequest(getApplicationContext(), spinner, sharedpreferences.getString(MainActivity.Name,null), sharedpreferences.getString(MainActivity.Phone,null));

        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(v);
            }
        });

        door.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestManager requestManager = RequestManager.getRequestManager();
                requestManager.openDoor(getApplicationContext(), door, spinner.getSelectedItem().toString(), sharedpreferences.getString(MainActivity.Name,null), sharedpreferences.getString(MainActivity.Phone,null));
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*door.setEnabled(true);
                String doorToLock = spinner.getSelectedItem().toString();
                String isOpen = log_Activity.hmap.get(doorToLock);
                if(isOpen.equals("true")){
                    isOpen = "Fermer";
                    log_Activity.hmap.put(doorToLock, "false");
                }else{
                    isOpen = "Ouvrir";
                    log_Activity.hmap.put(doorToLock, "true");
                }
                door.setText(isOpen);*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                door.setEnabled(false);
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
