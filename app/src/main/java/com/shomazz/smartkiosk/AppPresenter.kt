package com.shomazz.smartkiosk

import com.shomazz.smartkiosk.mvp.BasePresenter
import javax.inject.Inject

class AppPresenter @Inject constructor() : BasePresenter<AppView>() {

    fun subscribeToLanguageUpdates() {
        BaseApp.settingsInteractor.getUserSelectedLanguageUpdates()
            .doOnSubscribe(::addDisposable)
            .subscribe {
                view.applyNewLanguage()
            }
    }
}