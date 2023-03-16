package com.softsquared.template.kotlin.src.main.home.models

import com.softsquared.template.kotlin.src.comment.models.LikeOn

data class ResultHomePost(
    val commentShowStatus: Int,
    val content: String,
    val createdAt: String,
    val likeCount: Int,
    val likeOn: LikeOn,
    val likeShowStatus: Int,
    val photos: List<Photo>,
    val place: String?,
    val postId: Int,
    val profileName: String,
    val profilePicture: String,
    val scrapOn: ScrapOn,
    val tagWord: List<String?>,
    val updatedAt: String
)