package com.softsquared.template.kotlin.src.start.login

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.start.login.models.LoginRequest
import com.softsquared.template.kotlin.src.start.login.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val loginFragmentInterface: LoginFragmentInterface) {

    fun tryPostLogin(loginRequest: LoginRequest) {
        val loginRetrofitInterface =
            ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postLogin(loginRequest)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    loginFragmentInterface.onPostLoginSuccess(response.body() as LoginResponse)
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    loginFragmentInterface.onPostLoginFailure(t.message ?: "통신 오류")
                }

            })
    }
}