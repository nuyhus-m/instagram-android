package com.softsquared.template.kotlin.src.add.upload.models

import okhttp3.MultipartBody

data class Photo(
    val photoUrl: String,
    val userTagId: List<String?>
)