package com.shomazz.smartkiosk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), FragmentController {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
