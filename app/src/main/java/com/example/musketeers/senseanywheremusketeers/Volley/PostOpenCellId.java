package com.example.musketeers.senseanywheremusketeers.Volley;

import android.app.VoiceInteractor;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musketeers.senseanywheremusketeers.Interface.VolleyCallback;

public class PostOpenCellId {
    private Context context;
    private String url = "https://eu1.unwiredlabs.com/v2/process.php";

    public PostOpenCellId(Context context) {
        this.context = context;
    }

    public void post(final VolleyCallback callback){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
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
    }
}
