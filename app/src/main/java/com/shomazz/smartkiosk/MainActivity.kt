package com.shomazz.smartkiosk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.shomazz.smartkiosk.presentation.auth.AuthFragment
import com.shomazz.smartkiosk.presentation.camera.InnerCameraActivity
import com.shomazz.smartkiosk.presentation.input.InputFragment
import com.shomazz.smartkiosk.presentation.menu.MenuFragment


class MainActivity : AppCompatActivity(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openAuthScreen()
    }

    override fun openAuthScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentFrame, AuthFragment.newInstance())
            .commit()
    }

    override fun openMenu() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentFrame, MenuFragment.newInstance(), MENU_TAG)
            .commit()
    }

    override fun openInputFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentFrame, InputFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun openQrCamera() {
        IntentIntegrator(this)
            .setCameraId(1)
            .setCaptureActivity(InnerCameraActivity::class.java)
            .initiateScan()
    }

    override fun returnToMenuWithResult(code: String?, popBackStack: Boolean) {
        with(supportFragmentManager) {
            (findFragmentByTag(MENU_TAG) as MenuFragment).onResult(code)
            if (popBackStack) popBackStack()
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            returnToMenuWithResult(result.contents, false)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        private const val MENU_TAG = "menu_fragment"
    }
}
