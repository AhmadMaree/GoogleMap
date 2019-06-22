package com.example.myapplication5.weatherapi;

import com.google.gson.annotations.SerializedName;

public class WeatherMain {


    @SerializedName("temp")
  String temp ;
    @SerializedName("temp_min")
  double tempmax;
  @SerializedName("temp_max")
  double tempmin;

  public String getTemp() {
    return temp;
  }

  public double getTempmax() {
    return tempmax;
  }

  public double getTempmin() {
    return tempmin;
  }
}
