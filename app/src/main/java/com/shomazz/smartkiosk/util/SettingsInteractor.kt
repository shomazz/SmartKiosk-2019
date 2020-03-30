package com.shomazz.smartkiosk.util

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import java.util.*


class SettingsInteractor {

    private var locale: Locale = Locale.getDefault()
    private val localeSubject = PublishSubject.create<Locale>()

    fun getUserSelectedLanguage(): Single<Locale> {
        return Single.just(locale)
    }

    fun getUserSelectedLanguageUpdates(): Observable<Locale> {
        return localeSubject.hide()
    }

    fun setLocale(locale: Locale): Completable {
        this.locale = locale
        return Completable.fromAction {
            localeSubject.onNext(locale)
        }
    }

}