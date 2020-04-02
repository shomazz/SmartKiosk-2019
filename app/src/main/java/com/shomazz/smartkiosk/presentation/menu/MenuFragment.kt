package com.shomazz.smartkiosk.presentation.menu

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.shomazz.smartkiosk.R
import com.shomazz.smartkiosk.util.BaseFragment
import com.shomazz.smartkiosk.util.KioskLanguages
import kotlinx.android.synthetic.main.fragment_menu.*
import javax.inject.Inject

class MenuFragment : BaseFragment(), MenuView {

    @Inject
    override lateinit var presenter: MenuPresenter
    private lateinit var toast: Toast

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.attach(this)
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
        menuChangeLangBtn.setOnClickListener(::onChangeLangClick)
    }

    override fun showLanguageDialog() {
        val languages = KioskLanguages.languageTagMap.keys.toTypedArray()
        if (context != null) {
            AlertDialog.Builder(context)
                .setItems(languages) { _, position ->
                    presenter.onLanguageClick(KioskLanguages.languageTagMap[languages[position]])
                }
                .create()
                .show()
        }
    }

    override fun onChangeLangClick(v: View) {
        presenter.onChangeLangClick()
    }

    override fun onQrClick(v: View) {
        presenter.onQrClick()
    }

    override fun onInputClick(v: View) {
        presenter.onInputClick()
    }

    override fun onResult(code: String?) {
        presenter.onIdReceived(code)
    }

    override fun showError(message: String) {
        if (::toast.isInitialized) {
            toast.setText(message)
            toast.show()
        } else {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
            toast.show()
        }
    }

    override fun showError(resId: Int) {
        showError(getString(resId))
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            activity?.window?.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
            menuProgressBar.visibility = View.VISIBLE
        } else {
            menuProgressBar.visibility = View.INVISIBLE
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MenuFragment()
    }
}
