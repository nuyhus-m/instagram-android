package com.softsquared.template.kotlin.src.main.profile.models

import com.google.gson.annotations.SerializedName
import com.softsquared.template.kotlin.config.BaseResponse
import com.softsquared.template.kotlin.src.main.home.models.ResultHomePost

data class ProfilePostResponse(
    @SerializedName("result") val post: List<ResultHomePost>
): BaseResponse()