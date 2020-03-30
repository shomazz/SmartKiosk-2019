package com.shomazz.smartkiosk

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.shomazz.smartkiosk.presentation.auth.AuthFragment
import com.shomazz.smartkiosk.presentation.camera.InnerCameraActivity
import com.shomazz.smartkiosk.presentation.input.InputFragment
import com.shomazz.smartkiosk.presentation.menu.MenuFragment
import java.util.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), Navigator, AppView {

    @Inject
    lateinit var appPresenter: AppPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as BaseApp).component.inject(this)
        setContentView(R.layout.activity_main)
        setupPresenter()
        openAuthScreen()
    }

    private fun setupPresenter() {
        appPresenter.attach(this)
        appPresenter.subscribeToLanguageUpdates()
    }

    override fun onStop() {
        super.onStop()
        appPresenter.onStop()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(applySelectedAppLanguage(newBase))
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
            .replace(
                R.id.fragmentFrame, MenuFragment.newInstance(),
                MENU_TAG
            )
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

    private fun applySelectedAppLanguage(context: Context): Context {
        val locale = BaseApp.settingsInteractor.getUserSelectedLanguage().blockingGet()
        val newConfig = Configuration(context.resources.configuration)
        Locale.setDefault(locale)
        newConfig.setLocale(locale)
        return context.createConfigurationContext(newConfig)
    }

    override fun applyNewLanguage() {
        recreate()
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
