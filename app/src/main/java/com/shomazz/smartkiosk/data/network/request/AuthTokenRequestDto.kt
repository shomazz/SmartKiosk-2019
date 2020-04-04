package com.shomazz.smartkiosk.data.network.request

data class AuthTokenRequestDto(
    val username: String,
    val userPassword: String
)