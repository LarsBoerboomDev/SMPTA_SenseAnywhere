package com.example.musketeers.senseanywheremusketeers.Models;

import android.content.Intent;

import java.sql.Timestamp;

public class TempLocation {
    //Hier komt de model met de temperatuur en locatie
    String id;
    String temperature;
    String humidity;
    String longtitude;
    String latitude;
    String address;
    String accuracy;
    String eventType;
    String date;
    String devicId;
    Timestamp time;

    public  TempLocation(){

    }

    public TempLocation(String id, String temperature, String humidity, String longtitude, String latitude, String address, String accuracy, String eventType, String date, String devicId) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.address = address;
        this.accuracy = accuracy;
        this.eventType = eventType;
        this.date = date;
        this.devicId = devicId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDevicId() {
        return devicId;
    }

    public void setDevicId(String devicId) {
        this.devicId = devicId;
    }

    public String getTempCalc(){
        String temp = String.valueOf(Integer.valueOf(getTemperature()) / 100);
        return temp;
    }
}
