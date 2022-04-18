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
    val name: String,
    val lat: String,
    val lng: String
)
