package com.vons.mvvm.util

import android.os.Environment
import com.google.gson.Gson

object JsonUtil {

    @JvmStatic
    val gson = Gson()
    fun toJsonString(obj: Any): String {
        return gson.toJson(obj)
    }

    inline fun <reified T : Any> jsonToObj(json: String): T {
        return gson.fromJson(json, T::class.java)
    }
}

fun <T> T.toJson(): String {
    return JsonUtil.gson.toJson(this)
}

inline fun <reified T> String.fromJson(): T {
    return JsonUtil.gson.fromJson(this, T::class.java)
}