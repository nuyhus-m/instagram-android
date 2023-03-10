package com.softsquared.template.kotlin.src.start.join.models

import com.google.gson.annotations.SerializedName

data class ResultJoinPN(
    @SerializedName("jwt") val jwt: String,
    @SerializedName("user_id") val userId: Int
)
