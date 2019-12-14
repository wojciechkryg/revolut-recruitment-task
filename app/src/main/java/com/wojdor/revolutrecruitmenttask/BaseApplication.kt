package com.wojdor.revolutrecruitmenttask

import android.app.Application
import com.wojdor.revolutrecruitmenttask.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BaseApplication)
            modules(appModules)
        }
    }
}