package com.anywhereapps.project.repo

import com.example.example.WeatherReport
import javax.inject.Inject

/**
 * Implementation of IRepo that's provided in the viewmodel.
 * */
class RepositoryImpl @Inject constructor(
    private val itemsDB: IItemsDatabase,
    private val iNetwork: INetwork
): IRepository {
    override suspend fun getWeatherReport(): WeatherReport {
         return iNetwork.getWeatherReport()
    }

    override suspend fun deleteAllItems() {
        itemsDB.deleteAllItems()
    }
}