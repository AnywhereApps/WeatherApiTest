package com.anywhereapps.project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database of the app that provides the DAOs
 * */
@Database(entities = [ItemEntity::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun itemDAO(): ItemDAO

    companion object {
        private var db: AppDB? = null

        fun initDB(context: Context): AppDB {
            db = Room.databaseBuilder(
                context.applicationContext,
                AppDB::class.java, "weather_db"
            ).build()
            return db!!
        }

        /**
         * Returns the initialized database without passing a context in.
         * (Implementation can be changed as desired)
         *
         * @throws [IllegalStateException] if database is not initialized yet
         * */
        fun getDB(): AppDB {
            db
                ?: throw IllegalStateException("Database has not been initialized yet. Can't call this method")
            return db!!
        }

    }
}