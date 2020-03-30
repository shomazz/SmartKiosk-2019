package com.shomazz.smartkiosk.presentation.input

import android.view.View
import com.shomazz.smartkiosk.mvp.BaseView

interface InputView: BaseView {

    fun getCode(): String

    fun onInputButtonClick(v: View)

}