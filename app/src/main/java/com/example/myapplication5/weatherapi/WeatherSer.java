package com.example.myapplication5.weatherapi;

import com.example.myapplication5.weatherapi.WeatherRespons;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherSer {
    //http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139
    @GET("/data/2.5/weather")
    Call<WeatherRespons> get(@Query("APPID") String tok, @Query("lat") String lat ,@Query("lon") String lon);



}
