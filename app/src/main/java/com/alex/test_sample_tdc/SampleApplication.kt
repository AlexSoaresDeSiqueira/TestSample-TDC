package com.alex.test_sample_tdc

import android.app.Application
import com.alex.test_sample_tdc.di.PROPERTY_BASE_URL
import com.alex.test_sample_tdc.di.networkModule
import com.alex.test_sample_tdc.di.repositoryModule
import com.alex.test_sample_tdc.feature.searchStarship.di.searchStarshipModule
import org.koin.core.context.startKoin

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            modules(listOf(
                networkModule,
                repositoryModule,
                searchStarshipModule
            ))

            properties(mapOf(
                PROPERTY_BASE_URL to BuildConfig.API_BASE
            ))
        }
    }
}