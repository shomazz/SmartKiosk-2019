package com.shomazz.smartkiosk.data.repository

import com.shomazz.smartkiosk.data.PreferencesDataStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PrefsRepository @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore
) {

    fun getToken(): Single<String> {
        return preferencesDataStore.getToken()
    }

    fun cacheToken(token: String): Completable {
        return preferencesDataStore.cacheToken(token)
    }

    fun getPrinterIp(): Single<String> {
        return preferencesDataStore.getPrinterIp()
    }

    fun cachePrinterIp(ip: String): Completable {
        return preferencesDataStore.cachePrinterIp(ip)
    }

}