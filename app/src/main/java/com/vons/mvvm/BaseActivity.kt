package com.vons.mvvm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.vons.mvvm.frag.MineFragmentDirections

abstract class BaseActivity : FragmentActivity() {
    inline fun <reified T : Activity> intent(needLogin: Boolean = false) {
        if (needLogin) {
            findNavController(R.id.fragment).navigate(MineFragmentDirections.actionMineFragmentToLoginFragment())
        } else {
            startActivity(Intent(this, T::class.java))
            finish()
        }
    }

    inline fun <reified T : Activity> intent(bundle: Bundle, needLogin: Boolean = false) {
        if (needLogin) {
            findNavController(R.id.fragment).navigate(MineFragmentDirections.actionMineFragmentToLoginFragment())
        } else {
            startActivity(Intent(this, T::class.java).apply {
                putExtras(bundle)
            })
            finish()
        }
    }
}