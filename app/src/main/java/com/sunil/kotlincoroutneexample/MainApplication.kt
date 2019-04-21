package com.sunil.kotlincoroutneexample

import android.app.Application
import com.sunil.kotlincoroutneexample.injection.remoteDataSourceModule
import com.sunil.kotlincoroutneexample.injection.repositoryModule
import com.sunil.kotlincoroutneexample.injection.viewModelModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin(this, listOf(
            remoteDataSourceModule,
            repositoryModule,
            viewModelModule))
    }
}