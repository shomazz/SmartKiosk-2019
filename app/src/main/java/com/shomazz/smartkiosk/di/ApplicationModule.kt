package com.shomazz.smartkiosk.di

import android.content.Context
import com.shomazz.smartkiosk.BaseApp
import dagger.Binds
import dagger.Module


@Module
abstract class ApplicationModule {

    @Binds
    abstract fun provideContext(application: BaseApp): Context
}