package com.anywhereapps.project.repo

import com.anywhereapps.project.network.Item
import com.anywhereapps.project.network.data.WeatherReport

/**
 * This interface is implemented in the `repo` package by RepositoryImpl class.
 *
 * */
interface IRepository {

    suspend fun getWeatherReport(latitude : String,
                                 longitude : String,
                                 exclude : String,
                                 appid : String): WeatherReport
    suspend fun deleteAllItems()
    suspend fun getCities() : List<Item>
    suspend fun saveCity(item : Item)
    suspend fun deleteCity(id : Int)


}