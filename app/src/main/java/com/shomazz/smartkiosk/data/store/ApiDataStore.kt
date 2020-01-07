package com.shomazz.smartkiosk.data.store

import io.reactivex.Single

class ApiDataStore {

    fun getAuthToken(login: String, password: String): Single<String> {
        return Single.fromCallable {
            Thread.sleep(4000)
            "$login;-=Token=-;$password"
        }
        //TODO("request")
    }

}