package com.shomazz.smartkiosk.presentation.menu

import android.view.View
import com.shomazz.smartkiosk.util.BaseView

interface MenuView : BaseView {

    fun onQrClick(v: View)

    fun onInputClick(v: View)

    fun showProgress(show: Boolean)

    fun onResult(code: String)

    fun showError(message: String)

}