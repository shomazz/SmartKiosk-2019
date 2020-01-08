package com.shomazz.smartkiosk.data.repository

import com.shomazz.smartkiosk.data.store.ApiDataStore
import io.reactivex.Single

class AuthRepository {

    private val apiDataStore = ApiDataStore()

    fun getAuthToken(
        login: String,
        password: String
    ): Single<String> {
        //TODO: add cache
        return apiDataStore.getAuthToken(login, password)
    }

}