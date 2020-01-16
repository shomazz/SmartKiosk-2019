package com.shomazz.smartkiosk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shomazz.smartkiosk.presentation.auth.AuthFragment
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

    override fun openQrFragment() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun returnToMenuWithResult(code: String) {
        with(supportFragmentManager) {
            (findFragmentByTag(MENU_TAG) as MenuFragment).onResult(code)
            popBackStack()
        }
    }

    companion object {
        private const val MENU_TAG = "menu_fragment"
    }
}
