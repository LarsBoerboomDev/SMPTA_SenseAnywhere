package com.example.musketeers.senseanywheremusketeers;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

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
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class deliver3 extends AppCompatActivity {
    String temperatureData;
    String locationJson;

    Button stopTracking;
    Button startTracking;
    Button back;

    Timer timer = new Timer();
    TimerTask fiveMinuteTask;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Integer> tempgem = new ArrayList<>();

    TextView gemTemp;
    WebView map;

    public void setTemperatureData(String temperatureData) {
        this.temperatureData = temperatureData;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitydeliverpage3);
        gemTemp = findViewById(R.id.textViewTemp);
        map = findViewById(R.id.webview);

        stopTracking = findViewById(R.id.buttonStopTracking);
        stopTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
            }
        });

        back = findViewById(R.id.buttonBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(deliver3.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        startTracking = findViewById(R.id.buttonStartTracking);
        startTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer = new Timer();
                timer.schedule(fiveMinuteTask = new TimerTask() {
                    @Override
                    public void run() {
                        start();
                    }
                }, 0, 60*5000);
            }
        });


         fiveMinuteTask = new TimerTask() {
            @Override
            public void run() {
                start();
            }
        };
        timer.schedule(fiveMinuteTask,0,60*5000);

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
            tempgem.add(Integer.parseInt(databaseJson.getParam2()));

            db.collection("temperatureloc1").add(tempLocation);
        }
        calcGemTemp();
        setMap(openCellIdLocation);
    }
//www.google.nl/maps/@51.4550291,5.4808759,15
    public void setMap(Return location){
        map.setWebViewClient(new WebViewClient());
        map.getSettings().setJavaScriptEnabled(true);
        //http://www.google.com/maps?q=37.423156,-122.084917
        String url ="http://google.com/maps?q=" + location.getLat() + "," + location.getLon();
        map.loadUrl("http://google.com/maps?q=" + location.getLat() + "," + location.getLon());
    }

    public void calcGemTemp(){
        int count = 0;
        for (int t : tempgem){
            count = count + t;
        }
        String gem = String.valueOf(count / tempgem.size());
        gemTemp.setText(gem);
        tempgem.clear();
    }
    public void setTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate =  mdformat.format(calendar.getTime());

    }
}
