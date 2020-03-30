package com.shomazz.smartkiosk

import android.app.Application
import com.shomazz.smartkiosk.di.ApplicationComponent
import com.shomazz.smartkiosk.di.ApplicationModule
import com.shomazz.smartkiosk.di.DaggerApplicationComponent
import com.shomazz.smartkiosk.util.SettingsInteractor

class BaseApp : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        setup()
    }

    fun setup() {
        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        component.inject(this)
    }

    companion object {
        lateinit var instance: BaseApp private set
        val settingsInteractor = SettingsInteractor()
    }

}