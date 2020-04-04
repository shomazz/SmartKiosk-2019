package com.shomazz.smartkiosk.presentation.camera

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.KeyEvent
import com.journeyapps.barcodescanner.CaptureManager
import com.shomazz.smartkiosk.R
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity
import kotlinx.android.synthetic.main.activity_inner_camera.*
import java.util.*


class InnerCameraActivity : LocaleAwareCompatActivity() {

    private lateinit var capture: CaptureManager

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        if (overrideConfiguration != null) {
            val uiMode = overrideConfiguration.uiMode
            overrideConfiguration.setTo(baseContext.resources.configuration)
            overrideConfiguration.uiMode = uiMode
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateLocale()
        setContentView(R.layout.activity_inner_camera)
        setupCamera(savedInstanceState)
        innerCameraBackBtn.setOnClickListener { onBackPressed() }
    }

    private fun updateLocale() {
        val tag = intent.getStringExtra(langExtraTag)
        if (!tag.isNullOrBlank()) {
            updateLocale(Locale.forLanguageTag(tag))
        }
    }

    private fun setupCamera(savedInstanceState: Bundle?) {
        capture = CaptureManager(this, qrScannerView)
        capture.initializeFromIntent(intent, savedInstanceState)
        capture.decode()
    }

    override fun onResume() {
        super.onResume()
        capture.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        capture.onSaveInstanceState(outState)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return qrScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        capture.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        val langExtraTag = "langTag"
    }
}
