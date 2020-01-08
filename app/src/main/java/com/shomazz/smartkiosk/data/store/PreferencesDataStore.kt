package com.shomazz.smartkiosk.data.store

import android.content.Context
import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Single

class PreferencesDataStore(context: Context) {

    private val APP_PREFS = "prefs"
    private val TOKEN_KEY = "token"
    private val prefs: SharedPreferences

    init {
        prefs = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
    }

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