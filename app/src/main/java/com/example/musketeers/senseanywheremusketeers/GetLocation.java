package com.example.musketeers.senseanywheremusketeers;

import android.Manifest;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.CellIdentityLte;
import android.telephony.CellInfo;
import android.telephony.CellInfoLte;
import android.telephony.TelephonyManager;

import com.example.musketeers.senseanywheremusketeers.Models.Cells;
import com.example.musketeers.senseanywheremusketeers.Models.Location;
import com.example.musketeers.senseanywheremusketeers.Models.Wifi;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class GetLocation {

    private Context context;

    public GetLocation(Context context) {
        this.context = context;

    }

    public String getLocationJson(){
        Location location = new Location();
        CellIdentityLte lte = null;


        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        3);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }


        TelephonyManager tel = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);

        WifiManager wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);

        List<ScanResult> scanResults = wifiManager.getScanResults();

        JSONArray cellList = new JSONArray();

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){

        }else{

            List<CellInfo> infos = tel.getAllCellInfo();
            for(int i = 0; i<infos.size(); ++i){
                CellInfo info = infos.get(i);
                if(info instanceof  CellInfoLte){
                    lte = ((CellInfoLte) info).getCellIdentity();
                }

            }

        }
        location.setAddress("1");
        location.setToken("4a0744e1f59c1d");
        location.setRadio("gsm");
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){

        }else{
            location.setMcc(String.valueOf(lte.getMcc()));
            location.setMnc(String.valueOf(lte.getMnc()));
            Cells Cell = new Cells();
            Cell.setCid(String.valueOf(lte.getCi()));
            Cell.setLac(String.valueOf(lte.getTac()));
            Cells[] cells = {Cell};
            location.setCells(cells);
            List<Wifi> theWIfiList = new ArrayList<>();
            for(ScanResult result : scanResults){
                Wifi wifi = new Wifi();
                wifi.setBssid(result.BSSID);
                wifi.setFrequency(String.valueOf(result.frequency));
                wifi.setSignal(String.valueOf(result.level));
                wifi.setChannel(String.valueOf(result.channelWidth));
                theWIfiList.add(wifi);
            }
            location.setWifi(theWIfiList);
        }
        Gson gson = new Gson();


       return  gson.toJson(location);
        
    }

}
