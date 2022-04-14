package com.anywhereapps.project.repo

import com.example.example.WeatherReport

/**
 * This interface is implemented in the `repo` package by RepositoryImpl class.
 *
 * */
interface IRepository {

    suspend fun getWeatherReport(): WeatherReport
    suspend fun deleteAllItems()


}