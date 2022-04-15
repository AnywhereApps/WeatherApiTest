package com.anywhereapps.project.repo

import com.anywhereapps.project.network.Item
import com.example.example.WeatherReport

/**
 * This interface is implemented in the `repo` package by RepositoryImpl class.
 *
 * */
interface IRepository {

    suspend fun getWeatherReport(): WeatherReport
    suspend fun deleteAllItems()
    suspend fun getCities() : List<Item>
    suspend fun saveCity(item : Item)


}