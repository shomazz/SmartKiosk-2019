package com.shomazz.smartkiosk.di

import com.shomazz.smartkiosk.BaseApp
import com.shomazz.smartkiosk.presentation.auth.AuthFragment
import dagger.Component

@PerApplication
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(baseApp: BaseApp)

    fun inject(authFragment: AuthFragment)

}