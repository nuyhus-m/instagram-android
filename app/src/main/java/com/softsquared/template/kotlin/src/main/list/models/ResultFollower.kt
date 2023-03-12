package com.softsquared.template.kotlin.src.main.list.models

data class ResultFollower(
    val connected_count: Int,
    val follower_count: Int,
    val followers: List<Follower>,
    val following_count: Int
)