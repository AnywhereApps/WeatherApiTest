package com.anywhereapps.project.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Item DAO.
 *
 * */
@Dao
interface ItemDAO {

    @Query("SELECT * FROM Items WHERE id = :id")
    suspend fun getItem(id: Int): ItemEntity

    @Query("SELECT * FROM Items")
    suspend fun getAllItems(): List<ItemEntity>

    @Insert
    suspend fun saveItems(items: List<ItemEntity>)

    @Insert
    suspend fun saveItem(item: ItemEntity)

    @Query("DELETE FROM Items")
    suspend fun deleteAllItems()

    @Query("DELETE FROM Items WHERE id = :id")
    suspend fun deleteItem(id: Int)
}