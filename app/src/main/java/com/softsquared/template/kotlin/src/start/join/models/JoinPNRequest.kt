package com.softsquared.template.kotlin.src.start.join.models

import com.google.gson.annotations.SerializedName

data class JoinPNRequest(
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("birth_date") val birthDate: String,
    @SerializedName("nickname") val nickName: String,
    @SerializedName("password") val password: String
)