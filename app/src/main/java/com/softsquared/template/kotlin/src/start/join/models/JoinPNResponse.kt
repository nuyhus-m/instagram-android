package com.softsquared.template.kotlin.src.start.join.models

import com.google.gson.annotations.SerializedName
import com.softsquared.template.kotlin.config.BaseResponse

data class JoinPNResponse(
    @SerializedName("result") val result: ResultJoinPN
) : BaseResponse()
