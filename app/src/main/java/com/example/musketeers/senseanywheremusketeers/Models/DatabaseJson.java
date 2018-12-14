package com.example.musketeers.senseanywheremusketeers.Models;

public class DatabaseJson
{
    private String APID;

    private String DT_Event;

    private String RF;

    private String DeviceID;

    private String Param2;

    private String Event;

    private String Param1;

    private String ID;

    private String Flags;

    public String getAPID ()
    {
        return APID;
    }

    public void setAPID (String APID)
    {
        this.APID = APID;
    }

    public String getDT_Event ()
    {
        return DT_Event;
    }

    public void setDT_Event (String DT_Event)
    {
        this.DT_Event = DT_Event;
    }

    public String getRF ()
    {
        return RF;
    }

    public void setRF (String RF)
    {
        this.RF = RF;
    }

    public String getDeviceID ()
    {
        return DeviceID;
    }

    public void setDeviceID (String DeviceID)
    {
        this.DeviceID = DeviceID;
    }

    public String getParam2 ()
    {
        return Param2;
    }

    public void setParam2 (String Param2)
    {
        this.Param2 = Param2;
    }

    public String getEvent ()
    {
        return Event;
    }

    public void setEvent (String Event)
    {
        this.Event = Event;
    }

    public String getParam1 ()
    {
        return Param1;
    }

    public void setParam1 (String Param1)
    {
        this.Param1 = Param1;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getFlags ()
    {
        return Flags;
    }

    public void setFlags (String Flags)
    {
        this.Flags = Flags;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [APID = "+APID+", DT_Event = "+DT_Event+", RF = "+RF+", DeviceID = "+DeviceID+", Param2 = "+Param2+", Event = "+Event+", Param1 = "+Param1+", ID = "+ID+", Flags = "+Flags+"]";
    }
}
