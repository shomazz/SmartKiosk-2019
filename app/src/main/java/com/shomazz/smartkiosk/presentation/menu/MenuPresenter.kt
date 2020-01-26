package com.shomazz.smartkiosk.presentation.menu

import com.shomazz.smartkiosk.R
import com.shomazz.smartkiosk.domain.model.User
import com.shomazz.smartkiosk.domain.usecase.GetUserUseCase
import com.shomazz.smartkiosk.util.BasePresenter
import com.shomazz.smartkiosk.util.SimpleSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MenuPresenter @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : BasePresenter<MenuView>() {

    fun onQrClick() {
        navigator.openQrCamera()
    }

    fun onInputClick() {
        navigator.openInputFragment()
    }

    fun onIdReceived(id: String?) {
        if (id == null) {
            view.showError(R.string.cancelled)
        } else {
            view.showProgress(true)
            getUserUseCase.getUser(id)
                .doOnSubscribe(::addDisposable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(UserObserver())
        }
    }

    private inner class UserObserver : SimpleSingleObserver<User>() {

        override fun onSuccess(user: User) {
            super.onSuccess(user)
            view.showProgress(false)
            view.showError(user.toString())
            //TODO: print
        }

        override fun onError(e: Throwable) {
            super.onError(e)
            view.showProgress(false)
            view.showError(e.message ?: "Unknown Error")
            //TODO: normal onError
        }
    }
}