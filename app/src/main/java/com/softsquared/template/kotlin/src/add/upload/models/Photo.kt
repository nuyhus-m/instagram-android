package com.softsquared.template.kotlin.src.add.upload.models

import okhttp3.MultipartBody

data class Photo(
    val photoUrl: MultipartBody.Part,
    val userTagId: List<String>
)