package com.softsquared.template.kotlin.src.main.search.models

import com.softsquared.template.kotlin.config.BaseResponse

data class Search2Response(
    val result: List<ResultSearch2>
): BaseResponse()