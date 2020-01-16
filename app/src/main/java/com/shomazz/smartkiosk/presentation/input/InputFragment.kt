package com.shomazz.smartkiosk.presentation.input


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shomazz.smartkiosk.BaseApp
import com.shomazz.smartkiosk.R
import com.shomazz.smartkiosk.util.BaseFragment
import kotlinx.android.synthetic.main.fragment_input.*
import javax.inject.Inject

class InputFragment : BaseFragment(), InputView {

    @Inject
    override lateinit var presenter: InputPresenter

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
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputCodeBtn.setOnClickListener(::onInputButtonClick)
    }

    override fun getCode(): String = inputCodeEditText.text.toString()

    override fun onInputButtonClick(v: View) {
        presenter.onInputButtonClick()
    }

    companion object {
        fun newInstance() = InputFragment()
    }

}
