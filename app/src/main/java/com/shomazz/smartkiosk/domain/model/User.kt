package com.shomazz.smartkiosk.domain.model

data class User (
    val name: String,
    val organization: String,
    val payload: String
) {
    override fun toString(): String {
        return "$name from $organization. $payload"
    }
}