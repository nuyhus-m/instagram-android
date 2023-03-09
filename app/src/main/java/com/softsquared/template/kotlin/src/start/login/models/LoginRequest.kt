package com.softsquared.template.kotlin.src.start.login.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("id") val id: String,
    @SerializedName("password") val password: String,
)
