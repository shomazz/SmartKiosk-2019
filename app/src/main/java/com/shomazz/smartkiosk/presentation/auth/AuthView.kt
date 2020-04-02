package com.shomazz.smartkiosk.presentation.auth

import android.view.View
import androidx.annotation.StringRes
import com.shomazz.smartkiosk.util.BaseView

interface AuthView: BaseView {

    fun showMacAddressDialog()

    fun getLogin(): String

    fun getPassword(): String

    fun onLoginClick(button: View)

    fun onError(@StringRes id: Int)

    fun onError(msg: String)

    fun showProgress(show: Boolean)

}