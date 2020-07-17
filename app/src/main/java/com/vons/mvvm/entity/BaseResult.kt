package com.vons.mvvm.entity

open class BaseResult {
    var errorCode: Int = 0
    var errorMessage: String = ""
    var data: Any? = null
}