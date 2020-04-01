package com.shomazz.smartkiosk.di

import com.shomazz.smartkiosk.BaseApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class]
)
interface ApplicationComponent : AndroidInjector<BaseApp> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<BaseApp>

}