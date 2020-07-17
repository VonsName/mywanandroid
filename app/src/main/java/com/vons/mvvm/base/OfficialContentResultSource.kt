package com.vons.mvvm.base

import androidx.paging.PagingSource
import com.vons.mvvm.entity.ProjectContentEntity
import com.vons.mvvm.net.OfficialAccountRepository

class OfficialContentResultSource(
    private val repository: OfficialAccountRepository,
    private val id: Int
) :
    PagingSource<Int, ProjectContentEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProjectContentEntity> {
        val data = repository.officialDataById(id,params.key!!)
        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = params.key?.plus(1)
        )
    }
}