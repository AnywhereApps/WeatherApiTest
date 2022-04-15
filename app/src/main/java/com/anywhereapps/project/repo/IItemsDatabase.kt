package com.anywhereapps.project.repo

import com.anywhereapps.project.network.Item

/**
 * This interface is implemented in the `database` package by ItemsDatabaseImpl class.
 *
 * */
interface IItemsDatabase {

    suspend fun getAllItems(): List<Item>
    suspend fun saveItems(items: List<Item>)
    suspend fun saveItem(item: Item)
    suspend fun deleteAllItems()

}