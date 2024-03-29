package com.alex.test_sample_tdc

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

class SampleApplicationRunner : AndroidJUnitRunner() {

    override fun onStart() {
        RxJavaPlugins.setInitComputationSchedulerHandler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) }
        RxJavaPlugins.setInitIoSchedulerHandler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) }
        super.onStart()
    }

    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)
    }
    override fun newApplication(
        classLoader: ClassLoader,
        className: String,
        context: Context
    ): Application = newApplication(SampleApplicationTest::class.java, context)

    override fun finish(resultCode: Int, results: Bundle?) {
        super.finish(resultCode, results)
    }
}

