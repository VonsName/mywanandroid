package com.vons.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.vons.mvvm.adapter.RvOfficialContentAdapter
import com.vons.mvvm.base.OfficialContentResultSource
import com.vons.mvvm.entity.ProjectContentEntity
import com.vons.mvvm.entity.SystemEntity
import com.vons.mvvm.net.OfficialAccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OfficialAccountViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application

    val officialAccountLiveData: MutableLiveData<List<SystemEntity>> = MutableLiveData()
    var officialContentAdapter: RvOfficialContentAdapter? = null
    private val repository = OfficialAccountRepository(context)
    var needScrollToTop = true
    fun officialAccount() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val officialAccount = repository.officialAccount()
            withContext(Dispatchers.Main) {
                officialAccountLiveData.value = officialAccount
            }
        }
    }

    fun officialDataById(id: Int): LiveData<PagingData<ProjectContentEntity>> {
        return Pager(PagingConfig(20), 1) {
            OfficialContentResultSource(repository, id)
        }.liveData
    }
}