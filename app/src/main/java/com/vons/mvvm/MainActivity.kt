package com.vons.mvvm

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.vons.mvvm.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(dataBinding.bottomNavigationView, navController)
    }
}