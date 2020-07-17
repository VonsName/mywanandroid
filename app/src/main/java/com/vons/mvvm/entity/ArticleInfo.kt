package com.vons.mvvm.entity

data class ArticleInfo(
    val id: Int,
    val author: String,
    val chapterName: String,
    val publishTime: Long,
    var publishTimeStr: String,
    val shareDate: Long,
    val shareUser: String,
    val superChapterId: Int,// 一级分类的第一个子类目的id
    val superChapterName: String, // 一级分类的名称
    val title: String,
    val type: Int,
    val niceDate: String,
    val niceShareDate: String,
    val link: String
)

data class HomePageResult(val datas: List<ArticleInfo>) : PageInfo()

data class HomePageBannerResult(val data: MutableList<HomePageBannerInfo>)