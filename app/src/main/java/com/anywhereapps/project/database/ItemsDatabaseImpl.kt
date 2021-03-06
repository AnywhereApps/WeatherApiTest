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
            itemsDAO.getAllItems()?.map {
                Item(
                    id = it.id,
                    name = it.name,
                    lat = it.lat,
                    lng = it.lng
                )
            }
        }else {
            emptyList()
        }
    }

    override suspend fun saveItems(items: List<Item>) {

    }

    override suspend fun saveItem(it: Item) {
        itemsDAO.saveItem(ItemEntity(
            id = it.id,
            name = it.name?:"",
            lat = it.lat?:"",
            lng = it.lng?:""
        ))
    }

    override suspend fun deleteAllItems() {
        itemsDAO.deleteAllItems()
    }

    override suspend fun deleteItem(id: Int) {
        itemsDAO.deleteItem(id)
    }
}