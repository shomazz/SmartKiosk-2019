package com.shomazz.smartkiosk.di

import com.shomazz.smartkiosk.presentation.auth.AuthFragment
import com.shomazz.smartkiosk.presentation.input.InputFragment
import com.shomazz.smartkiosk.presentation.menu.MenuFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindAuthFragment(): AuthFragment

    @ContributesAndroidInjector
    abstract fun bindInputFragment(): InputFragment

    @ContributesAndroidInjector
    abstract fun bindMenuFragment(): MenuFragment

}