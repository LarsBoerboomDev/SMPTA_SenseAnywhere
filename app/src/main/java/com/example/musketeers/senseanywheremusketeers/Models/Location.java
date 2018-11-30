package com.example.musketeers.senseanywheremusketeers.Models;

import java.util.List;

public class Location
{
    private Cells[] cells;

    private List<Wifi> wifi;

    private String address;

    private String mcc;

    private String token;

    private String radio;

    private String mnc;

    public Cells[] getCells ()
    {
        return cells;
    }

    public void setCells (Cells[] cells)
    {
        this.cells = cells;
    }

    public List<Wifi> getWifi ()
    {
        return wifi;
    }

    public void setWifi (List<Wifi> wifi)
    {
        this.wifi = wifi;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getMcc ()
    {
        return mcc;
    }

    public void setMcc (String mcc)
    {
        this.mcc = mcc;
    }

    public String getToken ()
    {
        return token;
    }

    public void setToken (String token)
    {
        this.token = token;
    }

    public String getRadio ()
    {
        return radio;
    }

    public void setRadio (String radio)
    {
        this.radio = radio;
    }

    public String getMnc ()
    {
        return mnc;
    }

    public void setMnc (String mnc)
    {
        this.mnc = mnc;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cells = "+cells+", wifi = "+wifi+", address = "+address+", mcc = "+mcc+", token = "+token+", radio = "+radio+", mnc = "+mnc+"]";
    }
}
