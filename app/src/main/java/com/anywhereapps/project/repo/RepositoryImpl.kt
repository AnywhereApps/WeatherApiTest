package com.anywhereapps.project.repo

import com.anywhereapps.project.network.Item
import com.anywhereapps.project.network.data.WeatherReport
import javax.inject.Inject

/**
 * Implementation of IRepo that's provided in the viewmodel.
 * */
class RepositoryImpl @Inject constructor(
    private val itemsDB: IItemsDatabase,
    private val iNetwork: INetwork
): IRepository {
    override suspend fun getWeatherReport(latitude : String,
                                          longitude : String,
                                          exclude : String,
                                          appid : String): WeatherReport {
         return iNetwork.getWeatherReport(latitude, longitude, exclude, appid)
    }

    override suspend fun deleteAllItems() {
        itemsDB.deleteAllItems()
    }

    override suspend fun getCities(): List<Item> {
        return itemsDB.getAllItems()
    }


    override suspend fun saveCity(item: Item) {
        itemsDB.saveItem(item)
    }

    override suspend fun deleteCity(id: Int) {
        itemsDB.deleteItem(id)
    }

}