package com.shomazz.smartkiosk.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.shomazz.smartkiosk.domain.usecase.GetCachedTokenUseCase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    private val APP_PREFS = "prefs"
    private val BASE_URL = "https://muroming.pythonanywhere.com"

    @Provides
    fun provideSharedPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
    }

    @Provides
    fun provideRetrofitInstance(getCachedTokenUseCase: GetCachedTokenUseCase): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", getCachedTokenUseCase.getToken().blockingGet())
                    .build()
                chain.proceed(newRequest)
            }.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
        //TODO: add setTimeout
    }
}