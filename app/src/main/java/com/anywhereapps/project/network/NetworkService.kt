package com.anywhereapps.project.network

import com.example.example.WeatherReport
import retrofit2.http.*

/**
 * Retrofit network service that will communicate to the REST API.
 *
 * */
interface NetworkService {



    //https://api.openweathermap.org/data/2.5/onecall?lat=28.7041&lon=77.1025&exclude=daily,minutely&appid=b05a3577e844d86544879d15814bd5a1
    @GET("/data/2.5/onecall")
    @Headers("Content-type: application/json; charset=UTF-8")
    suspend fun getWeatherReport(
    @Query("lat") latitude : String,
    @Query("lon") longitude : String,
    @Query("exclude") exclude : String,
    @Query("appid") appid : String,
): WeatherReport


}