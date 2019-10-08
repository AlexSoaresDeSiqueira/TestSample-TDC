package com.alex.test_sample_tdc.rules

import android.app.Activity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import com.alex.test_sample_tdc.di.PROPERTY_BASE_URL
import com.alex.test_sample_tdc.di.networkModule
import com.alex.test_sample_tdc.di.repositoryModule
import com.alex.test_sample_tdc.feature.searchStarship.di.searchStarshipModule
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.ExternalResource
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

class InstrumentedTestRule<T : Activity>(
    private val activity: Class<T>
) : TestRule, KoinTest {

    val serverRule = MockWebServer()
    private lateinit var activityScenario: ActivityScenario<T>

    override fun apply(base: Statement?, description: Description?): Statement {
        return RuleChain
            .outerRule(InstantTaskExecutorRule())
            .around(serverRule)
            .around(StartKoinRule())
            .apply(base, description)
    }

    inner class StartKoinRule : ExternalResource() {
        override fun before() {
            super.before()
            startKoin {
                modules(listOf(
                    networkModule,
                    repositoryModule,
                    searchStarshipModule
                ))

                properties(mapOf(
                    PROPERTY_BASE_URL to serverRule.url("/").toString()
                ))
            }
            activityScenario = ActivityScenario.launch(activity)
        }

        override fun after() {
            super.after()
            activityScenario.close()
            serverRule.shutdown()
            stopKoin()
        }
    }
}