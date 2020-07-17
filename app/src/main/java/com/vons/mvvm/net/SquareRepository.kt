package com.vons.mvvm.net

import android.content.Context
import com.vons.mvvm.entity.NavigationInfo
import com.vons.mvvm.entity.NavigationResult
import com.vons.mvvm.entity.SystemEntity
import com.vons.mvvm.entity.SystemResult
import com.vons.mvvm.util.JsonUtil

class SquareRepository(private val context: Context) {

    suspend fun getSystemInfo(): List<SystemEntity> {
        val baseResult = RetrofitHelper.getApiService(context).systemData()
        val systemResult = JsonUtil.jsonToObj<SystemResult>(JsonUtil.toJsonString(baseResult))
        return systemResult.data
    }

    suspend fun getNavigationInfo(): List<NavigationInfo> {
        val baseResult = RetrofitHelper.getApiService(context).navigationData()
        val systemResult = JsonUtil.jsonToObj<NavigationResult>(JsonUtil.toJsonString(baseResult))
        return systemResult.data
    }
}