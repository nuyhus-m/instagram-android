package com.softsquared.template.kotlin.src.main.search.models

import com.softsquared.template.kotlin.config.BaseResponse

data class SearchResponse(
    val result: List<ResultSearch>
): BaseResponse()