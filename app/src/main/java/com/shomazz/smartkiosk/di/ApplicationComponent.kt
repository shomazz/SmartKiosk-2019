package com.shomazz.smartkiosk.di

import com.shomazz.smartkiosk.BaseApp
import com.shomazz.smartkiosk.presentation.auth.AuthFragment
import com.shomazz.smartkiosk.presentation.input.InputFragment
import com.shomazz.smartkiosk.presentation.menu.MenuFragment
import dagger.Component

@PerApplication
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(baseApp: BaseApp)

    fun inject(authFragment: AuthFragment)

    fun inject(menuFragment: MenuFragment)

    fun inject(inputFragment: InputFragment)

}