package com.softsquared.template.kotlin.src.start.login.models

import com.google.gson.annotations.SerializedName

data class ResultLogin(
    @SerializedName("jwt") val jwt: String,
    @SerializedName("user_id") val userId: Int
)