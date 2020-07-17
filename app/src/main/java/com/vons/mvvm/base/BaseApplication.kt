package com.vons.mvvm.base

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.widget.Toast
import kotlin.system.exitProcess

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }


    companion object {
        private lateinit var INSTANCE: Application
        fun getContext(): Context {
            return INSTANCE
        }
    }

    fun exit() {
        exitProcess(0)
    }
}

class Exit {
    var exit: Boolean = false
    fun doExit() {
        exit = true
        val handlerThread = HandlerThread("task")
        handlerThread.start()
        Handler(handlerThread.looper).postDelayed({
            exit = false
        }, 2000)
    }
}

fun Context.toast(content: String?, duration: Int = Toast.LENGTH_SHORT) {
    content ?: return
    Toast.makeText(this, content, duration).show()
}

fun toast(content: String?, duration: Int = Toast.LENGTH_SHORT) {
    BaseApplication.getContext().toast(content, duration)
}