package com.softsquared.template.kotlin.src.start.login

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.google.android.material.textfield.TextInputEditText
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.DialogBirthdayBinding
import com.softsquared.template.kotlin.databinding.DialogLoginErrorBinding
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

            if(binding.loginId.text.toString().isEmpty()) {
                binding.loginId.requestFocus()
                showKeyboard(binding.loginId)
            } else if(binding.loginPw.text.toString().isEmpty()) {
                binding.loginPw.requestFocus()
                showKeyboard(binding.loginPw)
            } else {
                hideKeyboard(requireActivity())
                val id = binding.loginId.text.toString()
                val password = binding.loginPw.text.toString()
                val loginRequest = LoginRequest(id, password)
                LoginService(this).tryPostLogin(loginRequest)
            }
        }
    }

    private fun showKeyboard(editText: TextInputEditText){
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }

    private fun hideKeyboard(activity: Activity){
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.window.decorView.applicationWindowToken, 0)
    }

    override fun onPostLoginSuccess(response: LoginResponse) {
        if(response.code == 1000){
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        } else {
            val errorDialog = Dialog(requireContext())
            val loginErrorDialogBinding : DialogLoginErrorBinding = DialogLoginErrorBinding.inflate(layoutInflater)
            errorDialog.setContentView(loginErrorDialogBinding.root)

            errorDialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            errorDialog.show()

            loginErrorDialogBinding.loginErrorBtn.setOnClickListener {
                errorDialog.dismiss()
                binding.loginPw.text?.clear()
            }
        }
    }

    override fun onPostLoginFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}