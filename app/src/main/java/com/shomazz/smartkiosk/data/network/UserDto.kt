package com.shomazz.smartkiosk.data.network

import com.google.gson.annotations.SerializedName

data class UserDto (
    @SerializedName("name") val name: String
)