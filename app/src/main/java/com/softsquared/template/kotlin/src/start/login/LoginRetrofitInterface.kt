package com.softsquared.template.kotlin.src.start.login

import com.softsquared.template.kotlin.src.start.login.models.LoginRequest
import com.softsquared.template.kotlin.src.start.login.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitInterface {
    @POST("/app/users/login")
    fun postLogin(@Body params: LoginRequest): Call<LoginResponse>
}