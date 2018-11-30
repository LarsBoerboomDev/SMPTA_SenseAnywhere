package com.example.musketeers.senseanywheremusketeers.Models;

public class Cells
{
    private String cid;

    private String lac;

    public String getCid ()
    {
        return cid;
    }

    public void setCid (String cid)
    {
        this.cid = cid;
    }

    public String getLac ()
    {
        return lac;
    }

    public void setLac (String lac)
    {
        this.lac = lac;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cid = "+cid+", lac = "+lac+"]";
    }
}
