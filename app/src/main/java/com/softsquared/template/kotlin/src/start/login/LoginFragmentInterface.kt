package com.softsquared.template.kotlin.src.start.login

import com.softsquared.template.kotlin.src.start.login.models.LoginResponse

interface LoginFragmentInterface {

    fun onPostLoginSuccess(response: LoginResponse)

    fun onPostLoginFailure(message: String)
}