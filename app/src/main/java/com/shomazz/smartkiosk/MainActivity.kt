package com.shomazz.smartkiosk

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import com.google.zxing.integration.android.IntentIntegrator
import com.shomazz.smartkiosk.presentation.auth.AuthFragment
import com.shomazz.smartkiosk.presentation.camera.CustomIntentIntegrator
import com.shomazz.smartkiosk.presentation.camera.InnerCameraActivity
import com.shomazz.smartkiosk.presentation.input.InputFragment
import com.shomazz.smartkiosk.presentation.menu.MenuFragment
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import java.util.*
import javax.inject.Inject


class MainActivity : LocaleAwareCompatActivity(), Navigator, HasAndroidInjector, SettingsHelper {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = fragmentInjector

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        if (overrideConfiguration != null) {
            val uiMode = overrideConfiguration.uiMode
            overrideConfiguration.setTo(baseContext.resources.configuration)
            overrideConfiguration.uiMode = uiMode
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportFragmentManager.backStackEntryCount == 0)
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
            .addToBackStack(MENU_TAG)
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
        CustomIntentIntegrator(this)
            .setLanguageTag(Locale.getDefault().toLanguageTag())
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
