package com.shomazz.smartkiosk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shomazz.smartkiosk.presentation.auth.AuthFragment
import com.shomazz.smartkiosk.presentation.MenuFragment

class MainActivity : AppCompatActivity(),
    Navigator {

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

    override fun openMainMenu() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentFrame, MenuFragment.newInstance())
            .commit()
    }
}
