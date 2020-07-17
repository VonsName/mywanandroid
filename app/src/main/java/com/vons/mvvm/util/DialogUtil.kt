package com.vons.mvvm.util

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import com.vons.mvvm.R

object DialogUtil {

    fun confirm(context: Context, tips: String, callBack: DialogConfirmListener) {
        var dialog: AlertDialog? = null
        AlertDialog.Builder(context).apply {
            val view = LayoutInflater.from(context).inflate(R.layout.tip_dialog, null, false)
            view.findViewById<TextView>(R.id.tvContent).text = tips
            setView(view)
            setPositiveButton("确定") { _, _ ->
                callBack.onClick()
                dialog?.dismiss()
            }
            setNegativeButton("取消") { _, _ ->
                dialog?.dismiss()
            }
        }.create().also { dialog = it }.show()
    }

    fun tips(context: Context, tips: String, callBack: DialogConfirmListener) {
        var dialog: AlertDialog? = null
        AlertDialog.Builder(context).apply {
            val view = LayoutInflater.from(context).inflate(R.layout.tip_dialog, null, false)
            view.findViewById<TextView>(R.id.tvContent).text = tips
            setView(view)
            setPositiveButton("确定") { _, _ ->
                callBack.onClick()
                dialog?.dismiss()
            }
        }.create().also { dialog = it }.show()
    }
}

interface DialogConfirmListener {
    fun onClick()
}