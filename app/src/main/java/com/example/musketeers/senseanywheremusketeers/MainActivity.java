package com.example.musketeers.senseanywheremusketeers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.CellIdentityLte;
import android.telephony.CellInfo;
import android.telephony.CellInfoLte;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.musketeers.senseanywheremusketeers.Interface.ASyncResponse;
import com.example.musketeers.senseanywheremusketeers.Models.Cells;
import com.example.musketeers.senseanywheremusketeers.Models.Location;
import com.example.musketeers.senseanywheremusketeers.Models.Wifi;
import com.example.musketeers.senseanywheremusketeers.Volley.PostOpenCellId;
import com.example.musketeers.senseanywheremusketeers.Volley.VolleyCalls;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

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
