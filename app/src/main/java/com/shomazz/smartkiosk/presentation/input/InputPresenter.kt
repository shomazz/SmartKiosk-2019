package com.shomazz.smartkiosk.presentation.input

import com.shomazz.smartkiosk.util.BasePresenter
import javax.inject.Inject

class InputPresenter @Inject constructor(): BasePresenter<InputView>() {

    fun onInputButtonClick(){
        val id = view.getCode()
        navigator.returnToMenuWithResult(id, true)
    }

}