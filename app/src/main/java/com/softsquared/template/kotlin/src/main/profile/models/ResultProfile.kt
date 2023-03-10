package com.softsquared.template.kotlin.src.main.profile.models

data class ResultProfile(
    val account_status: String,
    val birth_date: String,
    val connected_count: Int,
    val connected_friend_profiles: List<ConnectedFriendProfile>,
    val created_at: String,
    val email_address: String,
    val follower_count: Int,
    val following_count: Int,
    val gender: String,
    val introduce: Any,
    val name: Any,
    val nickname: String,
    val phone_number: Any,
    val post_count: Int,
    val profile_image_url: String,
    val updated_at: String,
    val user_id: Int
)