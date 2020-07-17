package com.vons.mvvm.entity

data class SystemEntity(
    val id: Int,
    val children: List<SystemEntity>,
    val courseId: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)

data class SystemResult(val data: List<SystemEntity>)