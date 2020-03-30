package com.shomazz.smartkiosk.presentation.input

import com.shomazz.smartkiosk.mvp.BaseFragmentPresenter
import javax.inject.Inject

class InputPresenter @Inject constructor(): BaseFragmentPresenter<InputView>() {

    fun onInputButtonClick(){
        val id = view.getCode()
        navigator.returnToMenuWithResult(id, true)
    }

}