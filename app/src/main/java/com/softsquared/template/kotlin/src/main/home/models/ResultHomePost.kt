package com.softsquared.template.kotlin.src.main.home.models

data class ResultHomePost(
    val commentShowStatus: Int,
    val content: String,
    val createdAt: String,
    val likeCount: Int,
    val likeOn: Int,
    val likeShowStatus: Int,
    val photos: List<Photo>,
    val place: Any,
    val postId: Int,
    val profileName: String,
    val profilePicture: String,
    val scrapOn: Int,
    val tagWord: List<Any>,
    val updatedAt: String
)