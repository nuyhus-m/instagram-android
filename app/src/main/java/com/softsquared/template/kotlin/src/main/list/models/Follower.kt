package com.softsquared.template.kotlin.src.main.list.models

data class Follower(
    val follow_status: Int,
    val name: String,
    val nickname: String,
    val profile_image_url: String,
    val story_status: Int,
    val user_id: Int
)