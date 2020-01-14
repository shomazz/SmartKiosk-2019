package com.shomazz.smartkiosk.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.shomazz.smartkiosk.BaseApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    private val APP_PREFS = "prefs"

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return baseApp
    }

    @Provides
    @Singleton
    fun provideSharedPrefs(): SharedPreferences {
        return baseApp.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
    }
}