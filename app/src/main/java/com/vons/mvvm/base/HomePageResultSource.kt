package com.vons.mvvm.base

import androidx.paging.PagingSource
import com.vons.mvvm.entity.ArticleInfo
import com.vons.mvvm.net.HomePageRepository

class HomePageResultSource(private val repository: HomePageRepository) :
    PagingSource<Int, ArticleInfo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleInfo> {
        val homePage = repository.homePage(params.key!!)
        return LoadResult.Page(
            data = homePage,
            prevKey = null,
            nextKey = params.key?.plus(1)
        )
    }
}