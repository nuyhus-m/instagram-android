package com.softsquared.template.kotlin.src.comment

import com.softsquared.template.kotlin.src.comment.models.CommentResponse

interface CommentFragmentInterface {

    fun onGetCommentsSuccess(response: CommentResponse)

    fun onGetCommentsFailure(message: String)
}