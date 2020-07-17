package com.vons.mvvm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.vons.mvvm.base.HomePageResultSource
import com.vons.mvvm.entity.ArticleInfo
import com.vons.mvvm.entity.HomePageBannerInfo
import com.vons.mvvm.net.HomePageRepository
import com.vons.mvvm.net.launch

class HomePageViewModel(application: Application) : AndroidViewModel(application) {
    private var context: Context = application

    fun getHomePageInfo(): LiveData<PagingData<ArticleInfo>> {
        val homePageRepository = HomePageRepository(context)
        return Pager(PagingConfig(10), 0) { HomePageResultSource(homePageRepository) }.liveData
    }

    var bannerLiveData: MutableLiveData<MutableList<HomePageBannerInfo>> = MutableLiveData()
    fun getHomePageBanner() {
        viewModelScope.launch(context, {
            HomePageRepository(context).banner()
        }, {
            bannerLiveData.value = it
        }, {
            println(it.message)
        })
//        viewModelScope.launch(context = Dispatchers.IO) {
//            kotlin.runCatching {
//                HomePageRepository(context).banner()
//            }.onSuccess {
//                withContext(Dispatchers.Main) {
//                    bannerLiveData.value = it
//                }
//            }.onFailure {
//                it.printStackTrace()
//            }
//            val banner = HomePageRepository(context).banner()
//            withContext(Dispatchers.Main) {
//                bannerLiveData.value = banner
//            }
//        }
    }
}