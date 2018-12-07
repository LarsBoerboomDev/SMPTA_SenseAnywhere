package com.example.musketeers.senseanywheremusketeers.Models;

public class Return
{
    private String balance;

    private String lon;

    private String address;

    private String status;

    private String accuracy;

    private String lat;

    public String getBalance ()
    {
        return balance;
    }

    public void setBalance (String balance)
    {
        this.balance = balance;
    }

    public String getLon ()
    {
        return lon;
    }

    public void setLon (String lon)
    {
        this.lon = lon;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getAccuracy ()
    {
        return accuracy;
    }

    public void setAccuracy (String accuracy)
    {
        this.accuracy = accuracy;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [balance = "+balance+", lon = "+lon+", address = "+address+", status = "+status+", accuracy = "+accuracy+", lat = "+lat+"]";
    }
}