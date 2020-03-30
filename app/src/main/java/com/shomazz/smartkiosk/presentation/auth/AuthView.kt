package com.shomazz.smartkiosk.presentation.auth

import android.view.View
import com.shomazz.smartkiosk.mvp.BaseView

interface AuthView: BaseView {

    fun getLogin(): String

    fun getPassword(): String

    fun onLoginClick(button: View)

    fun onError(message: String)

    fun showProgress(show: Boolean)

}