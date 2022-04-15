package com.anywhereapps.project.repo

import com.example.example.WeatherReport

/**
 * The interface that will provide the network communication between the REST API and the app
 *
 * This interface is implemented in the `network` package by NetworkImpl
 * */
interface INetwork {
    suspend fun getWeatherReport(latitude : String,
                                 longitude : String,
                                 exclude : String,
                                 appid : String): WeatherReport

}