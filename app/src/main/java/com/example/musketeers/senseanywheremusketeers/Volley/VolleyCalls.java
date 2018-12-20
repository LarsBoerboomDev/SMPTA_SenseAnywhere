package com.example.musketeers.senseanywheremusketeers.Volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VolleyCalls {

    public void PostCellApi(final String locationJson, Context context, Response.Listener<String> listener , Response.ErrorListener errorListener){
        String url = "https://eu1.unwiredlabs.com/v2/process.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener){
            @Override
            public byte[] getBody() throws com.android.volley.AuthFailureError {
                String str = locationJson;
                return str.getBytes();
            }
            public String getBodyContentType()
            {
                return "application/json; charset=utf-8";
            }
        };
        requestQueue.add(request);


    }
    public void getLast2(Context context, Response.Listener<String> listener,
                         Response.ErrorListener errorListener){
        String url = "http://i339188.hera.fhict.nl/query/getallwithevent9last2.php";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET,url,listener,errorListener);
        queue.add(request);

    }


}
