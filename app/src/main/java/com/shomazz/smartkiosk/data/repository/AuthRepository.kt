package com.shomazz.smartkiosk.data.repository

import com.shomazz.smartkiosk.data.store.ApiDataStore
import io.reactivex.Single
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiDataStore: ApiDataStore
) {

    fun getAuthToken(
        login: String,
        password: String
    ): Single<String> {
        //TODO: add cache
        return apiDataStore.getAuthToken(login, password)
    }

}