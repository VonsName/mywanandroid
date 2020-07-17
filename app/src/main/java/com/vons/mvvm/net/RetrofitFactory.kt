package com.vons.mvvm.net

import android.content.Context
import com.vons.mvvm.base.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory {
    companion object {
        fun retrofit(context: Context): Retrofit {
            return Retrofit.Builder().baseUrl(Constants.COMMON_URL_HEADER)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                .client(httpClientBuilder(context).build())
                .build()

        }

        private fun httpClientBuilder(context: Context): OkHttpClient.Builder {
            return OkHttpClient.Builder().addInterceptor(RequestAndResponseInterceptor(context))
                .connectTimeout(Constants.DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
        }
    }
}