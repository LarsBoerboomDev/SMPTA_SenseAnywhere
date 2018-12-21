package com.example.musketeers.senseanywheremusketeers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.musketeers.senseanywheremusketeers.Models.DatabaseJson;
import com.example.musketeers.senseanywheremusketeers.Models.Return;
import com.example.musketeers.senseanywheremusketeers.Models.TempLocation;
import com.example.musketeers.senseanywheremusketeers.Volley.VolleyCalls;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

public class deliver3 extends AppCompatActivity implements OnMapReadyCallback {
    String temperatureData;
    String locationJson;
    private GoogleMap map;
    private MapView mapView;

    public void setTemperatureData(String temperatureData) {
        this.temperatureData = temperatureData;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView = findViewById(R.id.mapView2);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView2);
        mapFragment.getMapAsync(this);


        Timer timer = new Timer();
        TimerTask fiveMinuteTask = new TimerTask() {
            @Override
            public void run() {
                start();
            }
        };
        timer.schedule(fiveMinuteTask,01,1000*30*30);

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
                saveToFireBase();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }

    public void saveToFireBase(){
        Gson gson = new Gson();


        Type collectionType = new TypeToken<Collection<DatabaseJson>>(){}.getType();
        Collection<DatabaseJson> databaseJsons = gson.fromJson(temperatureData, collectionType);

        Return openCellIdLocation = gson.fromJson(locationJson, Return.class);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        for(DatabaseJson databaseJson : databaseJsons){
            TempLocation tempLocation = new TempLocation();
            tempLocation.setLatitude(openCellIdLocation.getLat());
            tempLocation.setLongtitude(openCellIdLocation.getLon());
            tempLocation.setAddress(openCellIdLocation.getAddress());
            tempLocation.setAccuracy(openCellIdLocation.getAccuracy());
            tempLocation.setTemperature(databaseJson.getParam2());
            tempLocation.setHumidity(databaseJson.getParam1());
            tempLocation.setDevicId(databaseJson.getDeviceID());
            tempLocation.setEventType(databaseJson.getEvent());
            tempLocation.setDate(databaseJson.getDT_Event());
            FirebaseApp.initializeApp(this);
            DatabaseReference myRef = database.getReference("senseanywhere-34968/data");
            myRef.push().setValue(tempLocation);
        }
        updateGUI(openCellIdLocation);
    }

    public void updateGUI(Return opencellIdLocation){
        MapView mapView = findViewById(R.id.mapView2);
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng((Double.valueOf(opencellIdLocation.getLat())), Double.valueOf(opencellIdLocation.getLon())));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        map.moveCamera(center);
        map.animateCamera(zoom);
        onMapReady(map);

    }
}
