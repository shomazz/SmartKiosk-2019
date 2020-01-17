package com.shomazz.smartkiosk.presentation.menu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.shomazz.smartkiosk.BaseApp
import com.shomazz.smartkiosk.R
import com.shomazz.smartkiosk.util.BaseFragment
import kotlinx.android.synthetic.main.fragment_menu.*
import javax.inject.Inject

class MenuFragment : BaseFragment(), MenuView {

    @Inject
    override lateinit var presenter: MenuPresenter

    override fun onAttach(context: Context) {
        (activity?.application as BaseApp).component
            .inject(this)
        presenter.attach(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuInputBtn.setOnClickListener(::onInputClick)
        menuQrBtn.setOnClickListener(::onQrClick)
    }

    override fun onQrClick(v: View) {
        presenter.onQrClick()
    }

    override fun onInputClick(v: View) {
        presenter.onInputClick()
    }

    override fun onResult(code: String) {
        presenter.onIdReceived(code)
    }

    override fun showError(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    override fun showProgress(show: Boolean) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        @JvmStatic
        fun newInstance() = MenuFragment()
    }
}
