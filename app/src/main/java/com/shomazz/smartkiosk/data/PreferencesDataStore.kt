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

    fun getPrinterIp(): Single<String> {
        return Single.fromCallable {
            prefs.getString(PRINTER_IP, "")
        }
    }

    fun cachePrinterIp(ip: String): Completable {
        return Completable.fromCallable {
            prefs.edit()
                .putString(PRINTER_IP, ip)
                .commit()
        }
    }

    companion object {
        const val TOKEN_KEY = "token"
        const val PRINTER_IP = "printer_ip"
    }

}