package com.softsquared.template.kotlin.src.comment.models

import com.softsquared.template.kotlin.config.BaseResponse

data class CommentResponse(
    val result: List<ResultComment>
): BaseResponse()