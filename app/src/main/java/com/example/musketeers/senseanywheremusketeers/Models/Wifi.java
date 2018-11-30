package com.example.musketeers.senseanywheremusketeers.Models;

public class Wifi
{
    private String bssid;

    private String signal;

    private String frequency;

    private String channel;

    public String getBssid ()
    {
        return bssid;
    }

    public void setBssid (String bssid)
    {
        this.bssid = bssid;
    }

    public String getSignal ()
    {
        return signal;
    }

    public void setSignal (String signal)
    {
        this.signal = signal;
    }

    public String getFrequency ()
    {
        return frequency;
    }

    public void setFrequency (String frequency)
    {
        this.frequency = frequency;
    }

    public String getChannel ()
    {
        return channel;
    }

    public void setChannel (String channel)
    {
        this.channel = channel;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [bssid = "+bssid+", signal = "+signal+", frequency = "+frequency+", channel = "+channel+"]";
    }
}
