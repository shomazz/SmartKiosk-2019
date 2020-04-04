package com.shomazz.smartkiosk.data.network

import com.shomazz.smartkiosk.data.network.request.AuthTokenRequestDto
import com.shomazz.smartkiosk.data.network.request.PrinterIpRequestDto
import com.shomazz.smartkiosk.data.network.request.UserRequestDto
import com.shomazz.smartkiosk.data.network.response.TokenDto
import com.shomazz.smartkiosk.data.network.response.UserDto
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ServerApi {

    companion object {
        const val BASE_URL = "http://192.168.1.67:8080"
    }

    @POST("/user/authenticate")
    fun getAuthToken(@Body authTokenRequestDto: AuthTokenRequestDto): Single<TokenDto>

    @POST("/user")
    fun getUserInfo(@Body userRequestDto: UserRequestDto): Single<UserDto>

    @POST("booth/set_print_ip")
    fun setPrinterIp(@Body printerIpRequestDto: PrinterIpRequestDto): Completable

}