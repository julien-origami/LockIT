package com.lockers.doorknocking;

import android.app.Activity;
import android.content.Context;
import android.os.Debug;
import android.provider.SyncStateContract;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by julienpons on 06/04/2017.
 */

public class RequestManager {

    private static RequestManager requestManager = null;
    private static String lastRequest;

    private RequestManager(){

    }

    public static RequestManager getRequestManager(){
        if(requestManager==null){
            requestManager = new RequestManager();
        }
        return requestManager;
    }

    public String authRequest(Context context, final EditText name, final EditText password, final MainActivity logActivity, final TextView errorTV){

        RequestQueue queue = Volley.newRequestQueue(context);

        String url ="http://192.168.43.2:5000/check";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            RequestManager.lastRequest = "Response is: "+ response;
                            Log.d("Correct Answer ", RequestManager.lastRequest);
                            logActivity.correctAuthentication();
                        }
                    }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        RequestManager.lastRequest = "That didn't work!";
                        Log.d("Error ", RequestManager.lastRequest);
                        errorTV.setText("Invalid Login / Password");
                    }
                }) {

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {

                        String credentials = name.getText()+":"+password.getText();
                        String auth = "Basic "+ Base64.encodeToString(credentials.getBytes(),Base64.NO_WRAP);


                        Map<String, String> headers= new HashMap<>();
                        headers.put("Authorization", auth);

                        return headers;
                    }
                };
            queue.add(request);
            return request.toString();
    }


}



//########### SIMPLE HTTP REQUEST ###########//
    /*// Instantiate the RequestQueue.
    RequestQueue queue = Volley.newRequestQueue(context);
    String url ="http://192.168.43.2:5000/";
    //String url ="http://172.16.60.57:5431/gpio.php?pin=7&status=0";

    // Request a string response from the provided URL.
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
        new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                RequestManager.lastRequest = "Response is: "+ response;
                Log.d("Correct Answer ", RequestManager.lastRequest);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                RequestManager.lastRequest = "That didn't work!";
                Log.d("Error ", RequestManager.lastRequest);
            }
    });
    queue.add(stringRequest);
    //Log.d("Debug request ", stringRequest.toString());
    //Log.d("Debug Answer ", lastRequest);
    return stringRequest.toString();*/
