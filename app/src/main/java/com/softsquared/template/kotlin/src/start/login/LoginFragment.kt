package com.softsquared.template.kotlin.src.start.login

import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentLoginBinding
import com.softsquared.template.kotlin.src.start.StartActivity

class LoginFragment :
    BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::bind, R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtnJoin.setOnClickListener {
            val act = activity as StartActivity
            act.fragmentController(resources.getString(R.string.join_p_n_fragment), t, t)
        }
    }
}