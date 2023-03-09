package com.softsquared.template.kotlin.src.start.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentLoginBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.start.StartActivity
import com.softsquared.template.kotlin.src.start.login.models.LoginRequest
import com.softsquared.template.kotlin.src.start.login.models.LoginResponse

class LoginFragment :
    BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::bind, R.layout.fragment_login), LoginFragmentInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtnJoin.setOnClickListener {
            val act = activity as StartActivity
            act.fragmentController(resources.getString(R.string.join_p_n_fragment), t, t)
        }

        binding.loginBtnLogin.setOnClickListener {
            val id = binding.loginId.text.toString()
            val password = binding.loginPw.text.toString()
            val loginRequest = LoginRequest(id, password)
            LoginService(this).tryPostLogin(loginRequest)
        }
    }

    override fun onPostLoginSuccess(response: LoginResponse) {
        if(response.code == 1000){
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
    }

    override fun onPostLoginFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}