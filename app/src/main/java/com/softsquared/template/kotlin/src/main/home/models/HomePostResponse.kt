package com.softsquared.template.kotlin.src.main.home.models

import com.google.gson.annotations.SerializedName
import com.softsquared.template.kotlin.config.BaseResponse

data class HomePostResponse(
    @SerializedName("result") val post: List<ResultHomePost>
): BaseResponse()