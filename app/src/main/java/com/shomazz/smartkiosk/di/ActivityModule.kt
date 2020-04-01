package com.shomazz.smartkiosk.di

import com.shomazz.smartkiosk.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(subcomponents = [MainActivityComponent::class])
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class, DataModule::class])
    abstract fun bindMainActivity(): MainActivity

}