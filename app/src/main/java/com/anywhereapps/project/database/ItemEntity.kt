package com.anywhereapps.project.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Item model that provides the Room annotations
 * */
@Entity(tableName = "Items")
class ItemEntity(
    @PrimaryKey
    val id: Int,
    val description: String,
    val title: String,
    val timestamp: String,
    val image: String,
    val date: String,
    val locationlineone: String,
    val locationlinetwo: String
)
