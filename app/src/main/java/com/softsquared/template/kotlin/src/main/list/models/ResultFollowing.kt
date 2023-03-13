package com.softsquared.template.kotlin.src.main.list.models

data class ResultFollowing(
    val connected_count: Int,
    val follower_count: Int,
    val following_count: Int,
    val followings: List<Following>
)