package com.softsquared.template.kotlin.src.main.home.models

import com.softsquared.template.kotlin.config.BaseResponse

data class HomeStoryResponse(
    val result: List<ResultHomeStory>
): BaseResponse()