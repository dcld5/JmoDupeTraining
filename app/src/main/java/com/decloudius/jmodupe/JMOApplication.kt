package com.decloudius.jmodupe

import android.app.Application
import com.decloudius.jmodupe.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class JMOApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@JMOApplication)
            modules(appModule)
        }
    }
}
