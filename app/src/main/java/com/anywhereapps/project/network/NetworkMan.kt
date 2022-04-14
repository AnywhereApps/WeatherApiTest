package com.anywhereapps.project.network

import com.anywhereapps.project.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkMan {

    private val httpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG){
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
    }.build()

    private val defaultBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)


    fun <T> createService(service: Class<T>): T {
        return defaultBuilder
            .baseUrl("https://api.openweathermap.org")
            .build()
            .create(service)
    }
}