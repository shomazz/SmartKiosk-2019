package com.shomazz.smartkiosk

interface Navigator {

    fun openAuthScreen()

    fun openMenu()

    fun openQrCamera()

    fun openInputFragment()

    fun returnToMenuWithResult(code: String?, popBackStack: Boolean)

}