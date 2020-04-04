package com.shomazz.smartkiosk.data.network.request

data class PrinterIpRequestDto(
    val ip: String,
    val boothId: String
)