package com.vons.mvvm.net

import android.content.Context

class RetrofitHelper {

    companion object {
        @JvmStatic
        private var apiService: ApiService? = null
        fun getApiService(context: Context): ApiService {
            return apiService ?: synchronized(this) {
                RetrofitFactory.retrofit(context).create(ApiService::class.java).also {
                    apiService = it
                }
            }
        }
    }
}