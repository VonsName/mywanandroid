package com.vons.mvvm.base

import androidx.paging.PagingSource
import com.vons.mvvm.entity.ProjectCategoryEntity
import com.vons.mvvm.entity.ProjectContentEntity
import com.vons.mvvm.net.ProjectRepository

class ProjectContentResultSource(private val repository: ProjectRepository, private val cid: Int) :
    PagingSource<Int, ProjectContentEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProjectContentEntity> {
        val data = repository.projectContent(params.key!!, cid = cid)
        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = params.key?.plus(1)
        )
    }
}