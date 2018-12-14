package com.example.musketeers.senseanywheremusketeers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musketeers.senseanywheremusketeers.Interface.ServerCallBack;
import com.example.musketeers.senseanywheremusketeers.Interface.VolleyCallback;

import org.json.JSONObject;

public class ServerCalls {

    Context context;

    private String url = "http://i339188.hera.fhict.nl/query/getallwithevent9last2.php";

    public ServerCalls(Context context) {
        this.context = context;


    }


    public void getLast2(Response.Listener<String> listener,
                         Response.ErrorListener errorListener){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET,url,listener,errorListener);
        queue.add(request);

    }

    public void get(final VolleyCallback callback){
        RequestQueue queue = Volley.newRequestQueue(context);
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                            callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
