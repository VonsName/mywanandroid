package com.vons.mvvm.entity

data class ProjectCategoryEntity(
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)

data class ProjectCategoryResult(val data: List<ProjectCategoryEntity>)