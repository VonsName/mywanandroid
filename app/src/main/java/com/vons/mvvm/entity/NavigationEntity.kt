package com.vons.mvvm.entity

data class NavigationEntity(
    val author: String,
    val audit: Int,
    val chapterId: Int,
    val chapterName: String,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val title: String
)

data class NavigationInfo(
    val cid: Int,
    val name: String,
    val articles: List<NavigationEntity>
)

data class NavigationResult(val data: List<NavigationInfo>)