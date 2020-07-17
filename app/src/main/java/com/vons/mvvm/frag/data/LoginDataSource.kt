package com.vons.mvvm.frag.data

import android.content.Context
import com.vons.mvvm.frag.data.model.LoggedInUser
import com.vons.mvvm.net.RetrofitHelper
import com.vons.mvvm.util.JsonUtil
import java.io.IOException


class LoginDataSource(private val context: Context) {

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        return try {
            val result = RetrofitHelper.getApiService(context).login(username, password)
            if (result.errorCode != 0) {
                Result.Error(Exception("登录失败"))
            }
            val fakeUser = JsonUtil.jsonToObj<LoggedInUser>(JsonUtil.toJsonString(result.data!!))
            Result.Success(fakeUser)
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}