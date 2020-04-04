package com.shomazz.smartkiosk.data.network.response

import com.google.gson.annotations.SerializedName

data class UserDto (
    @SerializedName("name") val name: String,
    @SerializedName("organization") val organization: String,
    @SerializedName("payload") val payload: String
)