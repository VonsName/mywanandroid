package com.vons.mvvm.net

import android.content.Context
import com.google.gson.Gson
import com.vons.mvvm.base.toast
import com.vons.mvvm.entity.ArticleInfo
import com.vons.mvvm.entity.HomePageBannerInfo
import com.vons.mvvm.entity.HomePageBannerResult
import com.vons.mvvm.entity.HomePageResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePageRepository(private val context: Context) : RemoteRepository {
    suspend fun homePage(page: Int = 0): List<ArticleInfo> {
        val homePage = RetrofitHelper.getApiService(context).homePage(page)
        val pageResult = Gson().fromJson(Gson().toJson(homePage.data), HomePageResult::class.java)
        return pageResult.datas
    }

    suspend fun banner(): MutableList<HomePageBannerInfo> {
        val banner = RetrofitHelper.getApiService(context).banner()
        val result = Gson().fromJson(Gson().toJson(banner), HomePageBannerResult::class.java)
        return result.data
    }
}


typealias Error = suspend (e: Throwable) -> Unit

fun <T> CoroutineScope.launch(
    context: Context,
    block: suspend () -> T,
    success: (T) -> Unit,
    error: Error? = null
) {
    this.launch {
        kotlin.runCatching {
            withContext(Dispatchers.IO) {
                block()
            }
        }.onSuccess {
//            withContext(Dispatchers.Main) {
            success(it)
//            }
        }.onFailure {
            it.printStackTrace()
            toast(it.message)
            error?.invoke(it)
        }
    }
}
