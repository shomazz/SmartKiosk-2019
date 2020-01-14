package com.shomazz.smartkiosk.data.store

import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PreferencesDataStore @Inject constructor(
    private val prefs: SharedPreferences
) {

    private val TOKEN_KEY = "token"

    fun getToken(): Single<String?> {
        return Single.fromCallable {
            prefs.getString(TOKEN_KEY, null)
        }
    }

    fun cacheToken(token: String): Completable {
        return Completable.fromCallable {
            prefs.edit().putString(TOKEN_KEY, token)
        }
    }

}