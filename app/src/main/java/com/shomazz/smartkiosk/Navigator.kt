package com.shomazz.smartkiosk

interface Navigator {

    fun openAuthScreen()

    fun openMenu()

    fun openQrFragment()

    fun openInputFragment()

    fun returnToMenuWithResult(code: String)

}