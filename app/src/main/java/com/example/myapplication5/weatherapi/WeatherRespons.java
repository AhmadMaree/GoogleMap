package com.example.myapplication5.weatherapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherRespons {

    @SerializedName("weather")
    List<Weather> mWeathers;
    @SerializedName("main")
    WeatherMain main ;

    public List<Weather> getmWeathers() {
        return mWeathers;
    }

    public void setmWeathers(List<Weather> mWeathers) {
        this.mWeathers = mWeathers;
    }

    public void setMain(WeatherMain main) {
        this.main = main;
    }

    public WeatherMain getMain() {
        return main;
    }
}
