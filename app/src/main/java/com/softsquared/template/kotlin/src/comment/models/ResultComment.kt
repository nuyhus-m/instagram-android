package com.softsquared.template.kotlin.src.comment.models

data class ResultComment(
    val bigCommentCount: Int,
    val comment: String,
    val commentId: Int,
    val createdAt: String,
    val groupId: Int,
    val likeCount: Int,
    val likeOn: LikeOn,
    val postId: Int,
    val profileName: String,
    val profilePicture: String,
    val updatedAt: String,
    val userId: Int
)