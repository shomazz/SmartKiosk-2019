package com.shomazz.smartkiosk.data.store

import io.reactivex.Single
import javax.inject.Inject

class ApiDataStore @Inject constructor() {

    fun getAuthToken(login: String, password: String): Single<String> {
        return Single.fromCallable {
            Thread.sleep(4000)
            "$login;-=Token=-;$password"
        }
        //TODO("request")
    }

}