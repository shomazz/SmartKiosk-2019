package com.shomazz.smartkiosk.presentation.camera

import android.app.Activity
import android.content.Intent
import com.google.zxing.integration.android.IntentIntegrator

class CustomIntentIntegrator(activity: Activity) : IntentIntegrator(activity) {

    private var languageTag: String? = null

    fun setLanguageTag(tag: String): IntentIntegrator {
        this.languageTag = tag
        return this
    }

    override fun createScanIntent(): Intent {
        return super.createScanIntent().apply {
            addExtra(InnerCameraActivity.langExtraTag, languageTag ?: "")
        }
    }
}