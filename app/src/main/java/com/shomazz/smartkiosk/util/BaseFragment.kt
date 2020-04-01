package com.shomazz.smartkiosk.util

import android.content.Context
import androidx.fragment.app.Fragment
import com.shomazz.smartkiosk.Navigator
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    abstract val presenter: BasePresenter<*>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        presenter.navigator = context as? Navigator
            ?: throw RuntimeException("$context must implement Navigator")
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

}