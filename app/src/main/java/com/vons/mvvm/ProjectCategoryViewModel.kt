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
import com.vons.mvvm.base.ProjectContentResultSource
import com.vons.mvvm.entity.ProjectCategoryEntity
import com.vons.mvvm.entity.ProjectContentEntity
import com.vons.mvvm.net.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProjectCategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application

    private val _categoryLiveData: MutableLiveData<List<ProjectCategoryEntity>> = MutableLiveData()
    val categoryLiveData: LiveData<List<ProjectCategoryEntity>> = _categoryLiveData

    //    private val _contentLiveData = MutableLiveData<PagingData<ProjectContentEntity>>()
//    var contentLiveData: LiveData<PagingData<ProjectContentEntity>> = _contentLiveData
    fun getProjectCategory() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val data = ProjectRepository(context).projectCategory()
            withContext(Dispatchers.Main) {
                _categoryLiveData.value = data
            }
        }
    }

    fun getProjectContentByCid(cid: Int): LiveData<PagingData<ProjectContentEntity>> {
        return Pager(PagingConfig(20), 0) {
            ProjectContentResultSource(
                ProjectRepository(context),
                cid
            )
        }.liveData

//
//        val data = Transformations.switchMap(liveData) {
//            _contentLiveData.postValue(it)
//            return@switchMap _contentLiveData
//        }
//        contentLiveData = liveData as MutableLiveData
//        return contentLiveData
    }
}