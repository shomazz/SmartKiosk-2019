package com.shomazz.smartkiosk

import com.shomazz.smartkiosk.di.DaggerApplicationComponent
import com.zeugmasolutions.localehelper.LocaleAwareApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BaseApp : LocaleAwareApplication(), HasAndroidInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.factory()
            .create(this)
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return injector
    }
}