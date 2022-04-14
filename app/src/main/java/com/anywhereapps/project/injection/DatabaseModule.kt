package com.anywhereapps.project.injection

import android.content.Context
import com.anywhereapps.project.database.AppDB
import com.anywhereapps.project.database.ItemDAO
import com.anywhereapps.project.database.ItemsDatabaseImpl
import com.anywhereapps.project.repo.IItemsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideItemsDatabase(
        itemDAO: ItemDAO
    ): IItemsDatabase {
        return ItemsDatabaseImpl(
            itemsDAO = itemDAO
        )
    }

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ) = AppDB.initDB(appContext)

    @Singleton
    @Provides
    fun provideItemDao(db: AppDB) = db.itemDAO()



}