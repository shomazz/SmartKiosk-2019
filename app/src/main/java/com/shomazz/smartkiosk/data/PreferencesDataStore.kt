package com.shomazz.smartkiosk.data

import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PreferencesDataStore @Inject constructor(
    private val prefs: SharedPreferences
) {

    fun getToken(): Single<String> {
        return Single.fromCallable {
            prefs.getString(TOKEN_KEY, "")
        }
    }

    fun cacheToken(token: String): Completable {
        return Completable.fromCallable {
            prefs.edit()
                .putString(TOKEN_KEY, token)
                .commit()
        }
    }

    fun getMacAddress(): Single<String> {
        return Single.fromCallable {
            prefs.getString(MAC_ADDRESS, "")
        }
    }

    fun cacheMacAddress(address: String): Completable {
        return Completable.fromCallable {
            prefs.edit()
                .putString(MAC_ADDRESS, address)
                .commit()
        }
    }

    companion object {
        const val TOKEN_KEY = "token"
        const val MAC_ADDRESS = "mac_address"
    }

}