package com.example.musketeers.senseanywheremusketeers;

import android.content.Context;

import com.example.musketeers.senseanywheremusketeers.Interface.VolleyCallback;

public class SendData {

    String sensorData= "";
    //1 get the data from the server
    //2 Get the location data
    //3 connect them
    //4 send the data back to the server

    private Context context;

    public SendData(Context context) {
        this.context = context;
    }

    public void getData(){
        ServerCalls calls = new ServerCalls(context);
        calls.get(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                sensorData = result;
            }
        });
    }
    public void getLocation(){
        GetLocation location = new GetLocation(context);
        String locationJson = location.getLocationJson();

    }



}