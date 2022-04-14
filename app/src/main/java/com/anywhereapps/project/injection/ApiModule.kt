package com.anywhereapps.project.injection

import com.anywhereapps.project.network.NetworkMan
import com.anywhereapps.project.network.NetworkImpl
import com.anywhereapps.project.network.NetworkService
import com.anywhereapps.project.repo.IItemsDatabase
import com.anywhereapps.project.repo.INetwork
import com.anywhereapps.project.repo.RepositoryImpl
import com.anywhereapps.project.repo.IRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService {
        return NetworkMan.createService(NetworkService::class.java)
    }


    @Singleton
    @Provides
    fun provideIRepository(
        database: IItemsDatabase,
        network: INetwork,
    ): IRepository {
        return RepositoryImpl(
            itemsDB = database,
            iNetwork = network
        )
    }

    @Singleton
    @Provides
    fun provideINetwork(
        networkService: NetworkService
    ): INetwork {
        return NetworkImpl(
            networkService = networkService
        )
    }


}