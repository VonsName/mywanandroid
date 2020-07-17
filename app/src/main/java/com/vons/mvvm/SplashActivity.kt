package com.vons.mvvm

import android.Manifest
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.vons.mvvm.base.toast
import com.vons.mvvm.util.DialogConfirmListener
import com.vons.mvvm.util.DialogUtil
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class SplashActivity(enabled: Boolean) : BaseActivity(), EasyPermissions.PermissionCallbacks {
    private val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        toast("写入权限被拒绝,应用即将退出")
        Thread.sleep(2000)
        finish()
    }


    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        intent<MainActivity>()
    }

    private fun init() {
        if (EasyPermissions.hasPermissions(this, *permissions)) {
            intent<MainActivity>()
        } else {
            DialogUtil.tips(this, "晚安中请求写入权限", object : DialogConfirmListener {
                override fun onClick() {
                    requestLocationAndCallPermission()
                }
            })
        }
    }

    @AfterPermissionGranted(WRITE_EXTERNAL_STORAGE)
    private fun requestLocationAndCallPermission() {
        val perms = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (EasyPermissions.hasPermissions(this, *perms)) {
            intent<MainActivity>()
        } else {
            EasyPermissions.requestPermissions(this, "请求写入权限", WRITE_EXTERNAL_STORAGE, *perms)
        }
    }

    companion object {
        private const val WRITE_EXTERNAL_STORAGE = 100
    }
}