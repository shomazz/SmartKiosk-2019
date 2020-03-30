package com.shomazz.smartkiosk.util

import android.content.Context
import androidx.fragment.app.Fragment
import com.shomazz.smartkiosk.Navigator
import com.shomazz.smartkiosk.mvp.BaseFragmentPresenter

abstract class BaseFragment : Fragment() {

    abstract val fragmentPresenter: BaseFragmentPresenter<*>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentPresenter.navigator = context as? Navigator
            ?: throw RuntimeException("$context must implement Navigator")
    }

    override fun onStop() {
        fragmentPresenter.onStop()
        super.onStop()
    }

}