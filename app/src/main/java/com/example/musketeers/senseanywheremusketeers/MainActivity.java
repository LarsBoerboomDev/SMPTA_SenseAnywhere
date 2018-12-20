package com.example.musketeers.senseanywheremusketeers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.musketeers.senseanywheremusketeers.Volley.VolleyCalls;

public class MainActivity extends AppCompatActivity {

    String temperatureData;
    String locationJson;

    public void setTemperatureData(String temperatureData) {
        this.temperatureData = temperatureData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmain);
        Button sendButton = findViewById(R.id.buttonSendData);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
                }
        });

    }

    public void start(){
        VolleyCalls volleyCalls = new VolleyCalls();
        volleyCalls.getLast2(this, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String theResponse = response;
                setTemperatureData(theResponse);
                getLocation();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
    public void getLocation(){
        GetLocation getLocation = new GetLocation(this);
        String json = getLocation.getLocationJson();
        VolleyCalls calls = new VolleyCalls();
        calls.PostCellApi(json, this, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.print(response);
                locationJson = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }
}
