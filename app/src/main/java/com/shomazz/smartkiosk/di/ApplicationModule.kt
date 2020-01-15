package com.shomazz.smartkiosk.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.shomazz.smartkiosk.BaseApp
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    companion object {
        private val APP_PREFS = "prefs"
        private val BASE_URL = "https://restcountries.eu"
    }

    @Provides
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }

    @Provides
    @PerApplication
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @PerApplication
    fun provideSharedPrefs(): SharedPreferences {
        return baseApp.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
    }
}