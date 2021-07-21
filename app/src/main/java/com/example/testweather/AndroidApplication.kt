package com.example.testweather

import android.app.Application
import com.example.testweather.data.AppSettings
import com.example.testweather.di.appModule
import com.example.testweather.di.networkModule
import com.example.testweather.di.uiModule
import com.example.testweather.utils.ThemeUtils
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Set Up Koin DI
        startKoin {
            androidContext(this@AndroidApplication)
            modules(appModule, networkModule, uiModule)
        }

        // Set Up App Theme
        val settings by inject<AppSettings>()
        ThemeUtils.setAppTheme(settings.theme)
    }
}