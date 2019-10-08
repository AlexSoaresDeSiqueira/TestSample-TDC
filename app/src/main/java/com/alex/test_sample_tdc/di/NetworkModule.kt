package com.alex.test_sample_tdc.di

import com.alex.test_sample_tdc.data.StarwarsApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val PROPERTY_BASE_URL = "baseUrl"

val networkModule = module {

    single {
        OkHttpClient
            .Builder()
            .build()
    }

    single {
        val baseUrl = getProperty<String>(PROPERTY_BASE_URL)
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(get())
            .build()
    }

    single {
        val retrofit: Retrofit = get()
        retrofit.create(StarwarsApi::class.java)
    }
}