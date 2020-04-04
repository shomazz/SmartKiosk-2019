package com.shomazz.smartkiosk.data.network

import com.shomazz.smartkiosk.data.network.request.AuthTokenRequestDto
import com.shomazz.smartkiosk.data.network.request.PrinterIpRequestDto
import com.shomazz.smartkiosk.data.network.request.UserRequestDto
import com.shomazz.smartkiosk.data.network.response.TokenDto
import com.shomazz.smartkiosk.data.network.response.UserDto
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class HttpClient @Inject constructor(
    private val retrofit: Retrofit
) {

    fun getAuthToken(
        login: String,
        password: String
    ): Single<TokenDto> {
        return retrofit
            .create(ServerApi::class.java)
            .getAuthToken(AuthTokenRequestDto(login, password))
    }

    fun getUserInfo(id: String): Single<UserDto> {
        return retrofit
            .create(ServerApi::class.java)
            .getUserInfo(UserRequestDto(id))
    }

    fun setPrinterIp(ip: String, kioskId: String): Completable {
        return retrofit
            .create(ServerApi::class.java)
            .setPrinterIp(PrinterIpRequestDto(ip, kioskId))
    }

}