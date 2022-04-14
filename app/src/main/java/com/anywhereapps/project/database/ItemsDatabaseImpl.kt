package com.anywhereapps.project.database

import com.anywhereapps.project.network.Item
import com.anywhereapps.project.repo.IItemsDatabase

/**
 * Implementation of IItemsDatabase that's provided by the repo package.
 *
 * */
class ItemsDatabaseImpl(
    private val itemsDAO: ItemDAO
) : IItemsDatabase {
    override suspend fun getAllItems(): List<Item> {
        return if (itemsDAO.getAllItems() != null){
  /*          itemsDAO.getAllItems()?.map {
                Item(
                    id = it.id,
                    description = it.description,
                    title = it.title,
                    timestamp = it.timestamp,
                    image = it.image,
                    date = it.date,
                    locationline1 = it.locationlineone,
                    locationline2 = it.locationlinetwo
                )
            }*/
            emptyList()
        }else {
            emptyList()
        }
    }

    override suspend fun saveItems(items: List<Item>) {

      /*  val values = items.map {
            ItemEntity(
                id = it.id,
                description = it.description?:"",
                title = it.title?:"",
                timestamp = it.timestamp?:"",
                image = it.image?:"",
                date = it.date?:"",
                locationlineone = it.locationline1?:"",
                locationlinetwo = it.locationline2?:""
            )
        }
        itemsDAO.saveItems(values)*/
    }


    override suspend fun deleteAllItems() {
        itemsDAO.deleteAllItems()
    }
}