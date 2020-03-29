package com.shomazz.smartkiosk.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class BitmapDrawer(
    private val width: Int = 400,
    private val height: Int = 400
) {

    private val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    private val canvas = Canvas(bitmap)
    private val paint = Paint()

    fun drawText(
        text: String,
        x: Float = 100f,
        y: Float = 100f,
        color: Int = Color.BLACK
    ): BitmapDrawer {
        paint.color = color
        canvas.drawText(text, x, y, paint)
        return this
    }

    fun get(): Bitmap {
        return bitmap
    }

}