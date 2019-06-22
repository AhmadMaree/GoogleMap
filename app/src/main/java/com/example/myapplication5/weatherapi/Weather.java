package com.example.myapplication5.weatherapi;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("main")
    String mMain;

    @SerializedName("description")
    String des;


    public void setDes(String des) {
        this.des = des;
    }

    public void setmMain(String mMain) {
        this.mMain = mMain;
    }

    public String getDes() {
        return des;
    }

    public String getmMain() {
        return mMain;
    }
}
