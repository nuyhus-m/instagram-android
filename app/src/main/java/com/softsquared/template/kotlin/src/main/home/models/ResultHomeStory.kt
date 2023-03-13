package com.softsquared.template.kotlin.src.main.home.models

data class ResultHomeStory(
    val nickname: String,
    val profile_image_url: String,
    val self_status: Int,
    val updated_at: String,
    val user_id: Int,
    val view_status: Int
)