package com.softsquared.template.kotlin.src.main.profile.models

import com.softsquared.template.kotlin.src.comment.models.LikeOn
import com.softsquared.template.kotlin.src.main.home.models.ScrapOn

data class ResultProfilePost(
    val commentShowStatus: Int,
    val content: String,
    val createdAt: String,
    val likeCount: Int,
    val likeOn: LikeOn,
    val likeShowStatus: Int,
    val photos: List<Photo>,
    val place: Any,
    val postId: Int,
    val profileName: String,
    val profilePicture: String,
    val scrapOn: ScrapOn,
    val tagWord: List<String>,
    val updatedAt: String
)