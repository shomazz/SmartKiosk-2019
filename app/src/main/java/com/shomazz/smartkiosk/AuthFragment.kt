package com.shomazz.smartkiosk

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment : Fragment() {

    private var fragmentController: FragmentController? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentController = context as? FragmentController
            ?: throw RuntimeException("$context must implement FragmentController")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginBtn.setOnClickListener {
            //TODO: auth request
            fragmentController?.openMainMenu()
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentController = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthFragment()
    }
}
