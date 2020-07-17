package com.vons.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vons.mvvm.entity.NavigationInfo
import com.vons.mvvm.entity.SystemEntity
import com.vons.mvvm.net.SquareRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SquareFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application
    val systemLiveData: MutableLiveData<List<SystemEntity>> = MutableLiveData()
    val navigationLiveData: MutableLiveData<List<NavigationInfo>> = MutableLiveData()
    private val squareRepository = SquareRepository(context)
    fun getSystemData() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val data = squareRepository.getSystemInfo()
            withContext(Dispatchers.Main) {
                systemLiveData.value = data
            }
        }
    }

    fun getNavigationData() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val data = squareRepository.getNavigationInfo()
            withContext(Dispatchers.Main) {
                navigationLiveData.value = data
            }
        }
    }
}