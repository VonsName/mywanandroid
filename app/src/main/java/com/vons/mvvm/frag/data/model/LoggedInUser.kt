package com.vons.mvvm.frag.data.model


data class LoggedInUser(
    val id: Int,
    val nickname: String,
    val publicName: String,
    val admin: Boolean,
    val chapterTops: List<String>,
    val collectIds: List<String>,
    val email: String,
    val icon: String,
    val token: String,
    val username: String
)