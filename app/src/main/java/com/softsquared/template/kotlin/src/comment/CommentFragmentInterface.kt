package com.softsquared.template.kotlin.src.comment

import com.softsquared.template.kotlin.src.comment.models.CommentResponse
import com.softsquared.template.kotlin.src.main.home.models.LikeResponse

interface CommentFragmentInterface {

    fun onGetCommentsSuccess(response: CommentResponse)

    fun onGetCommentsFailure(message: String)

    fun onPostCommentLikeSuccess(response: LikeResponse)

    fun onPostCommentLikeFailure(message: String)
}