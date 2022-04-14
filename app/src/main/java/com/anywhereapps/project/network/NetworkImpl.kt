package com.anywhereapps.project.network

import com.anywhereapps.project.repo.INetwork
import com.example.example.WeatherReport

/**
 * Implementation of INetwork that's provided by the repo package.
 *
 * */
class NetworkImpl(
    private val networkService: NetworkService
): INetwork {
    override suspend fun getWeatherReport(): WeatherReport {
         return networkService.getWeatherReport()
    }
}
