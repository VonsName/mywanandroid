package com.vons.mvvm.entity

data class ProjectContentEntity(
    val author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    val collect: Boolean,
    val desc: String,
    val envelopePic: String,
    val id: Int,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val projectLink: String,
    val title: String,
    var dateAndAuthor: String,
    val superChapterId: Int,
    val superChapterName: String
)

data class ProjectContentResult(val datas: List<ProjectContentEntity>)
