package com.anywhereapps.project.network

import com.anywhereapps.project.repo.INetwork
import com.anywhereapps.project.network.data.WeatherReport

/**
 * Implementation of INetwork that's provided by the repo package.
 *
 * */
class NetworkImpl(
    private val networkService: NetworkService
): INetwork {
    override suspend fun getWeatherReport(latitude : String,
                                          longitude : String,
                                          exclude : String,
                                          appid : String): WeatherReport {
         return networkService.getWeatherReport(latitude, longitude, exclude, appid)
    }
}
