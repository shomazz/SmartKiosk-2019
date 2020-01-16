package com.shomazz.smartkiosk.presentation.menu

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
        navigator.openQrFragment()
    }

    fun onInputClick() {
        navigator.openInputFragment()
    }

    fun onIdReceived(id: String) {
        view.showProgress()
        getUserUseCase.getUser(id)
            .doOnSubscribe(::addDisposable)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(UserObserver())
    }

    private inner class UserObserver : SimpleSingleObserver<List<User>>() {

        override fun onSuccess(user: List<User>) {
            super.onSuccess(user)
            view.hideProgress()
            view.showError(user[0].name)
            //TODO: onSuccess
        }

        override fun onError(e: Throwable) {
            super.onError(e)
            view.hideProgress()
            view.showError(e.message ?: "Unknown Error")
            //TODO: normal onError
        }
    }
}