package com.shomazz.smartkiosk.presentation.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.shomazz.smartkiosk.R
import com.shomazz.smartkiosk.util.BaseFragment
import kotlinx.android.synthetic.main.fragment_auth.*


class AuthFragment : BaseFragment(), AuthView {

    override lateinit var presenter: AuthPresenter

    override fun onAttach(context: Context) {
        presenter = AuthPresenter(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginBtn.setOnClickListener(::onLoginClick)
    }

    override fun getLogin() = loginEditText.text.toString()

    override fun getPassword() = passwordEditText.text.toString()

    override fun onLoginClick(button: View) {
        presenter.onLoginClick()
    }

    override fun onError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        //TODO("change")
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthFragment()
    }
}
