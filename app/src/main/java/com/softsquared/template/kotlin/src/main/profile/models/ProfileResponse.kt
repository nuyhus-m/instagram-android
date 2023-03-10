package com.softsquared.template.kotlin.src.main.profile.models

import com.google.gson.annotations.SerializedName
import com.softsquared.template.kotlin.config.BaseResponse

data class ProfileResponse(
    @SerializedName("result") val result: ResultProfile
): BaseResponse()