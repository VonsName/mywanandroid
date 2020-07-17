package com.vons.mvvm.net

import android.content.Context
import com.vons.mvvm.entity.*
import com.vons.mvvm.util.JsonUtil

class OfficialAccountRepository(private val context: Context) : RemoteRepository {

    suspend fun officialAccount(): List<SystemEntity> {
        val baseResult = RetrofitHelper.getApiService(context).officialAccount()
        val systemResult = JsonUtil.jsonToObj<SystemResult>(JsonUtil.toJsonString(baseResult))
        return systemResult.data
    }

    suspend fun officialDataById(id: Int, page: Int): List<ProjectContentEntity> {
        val baseResult = RetrofitHelper.getApiService(context).officialDataById(id, page)
        val pageResult = JsonUtil.jsonToObj<PageResult>(JsonUtil.toJsonString(baseResult.data!!))
        val officialContent =
            JsonUtil.jsonToObj<ProjectContentResult>(JsonUtil.toJsonString(pageResult))
        return officialContent.datas
    }
}