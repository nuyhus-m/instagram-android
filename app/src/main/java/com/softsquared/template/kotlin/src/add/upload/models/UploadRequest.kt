package com.softsquared.template.kotlin.src.add.upload.models

data class UploadRequest(
    val commentShowStatus: Int,
    val content: String,
    val likeShowStatus: Int,
    val photos: List<Photo>,
    val tagWord: List<String>,
    val place: String?
)