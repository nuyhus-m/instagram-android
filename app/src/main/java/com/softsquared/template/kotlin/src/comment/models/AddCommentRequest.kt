package com.softsquared.template.kotlin.src.comment.models

data class AddCommentRequest(
    val comment: String,
    val groupId: Int,
    val postId: Int
)