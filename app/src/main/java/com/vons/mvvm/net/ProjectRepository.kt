package com.vons.mvvm.net

import android.content.Context
import com.vons.mvvm.entity.*
import com.vons.mvvm.util.JsonUtil

class ProjectRepository(private val context: Context):RemoteRepository {
    suspend fun projectCategory(): List<ProjectCategoryEntity> {
        val result = RetrofitHelper.getApiService(context).projectCategory()
        val obj = JsonUtil.jsonToObj<ProjectCategoryResult>(JsonUtil.toJsonString(result))
        return obj.data
    }

    suspend fun projectContent(page: Int, cid: Int): List<ProjectContentEntity> {
        val result = RetrofitHelper.getApiService(context).getProjectContent(page, cid)
        val pageResult = JsonUtil.jsonToObj<PageResult>(JsonUtil.toJsonString(result.data!!))
        val data = JsonUtil.jsonToObj<ProjectContentResult>(JsonUtil.toJsonString(pageResult))
        return data.datas
    }
}