package com.example.weatherapp.network;

import com.example.weatherapp.model.Wheather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiManager {
    public static String BASE_URL = "https://dataservice.accuweather.com";

    @GET("/forecasts/v1/hourly/12hour/353412?apikey=NAULeGGTNBHdqKMlc2oddp3LCh4oEQC5&language=vi-vn&metric=true")
    Call<List<Wheather>> gethour();

    @GET("/forecasts/v1/daily/5day/353412?apikey=NAULeGGTNBHdqKMlc2oddp3LCh4oEQC5&language=vi-vn&metric=true")
    Call<List<Wheather>> getDay();
}
