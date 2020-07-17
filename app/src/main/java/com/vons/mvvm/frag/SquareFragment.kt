package com.vons.mvvm.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.vons.mvvm.R
import com.vons.mvvm.adapter.NAVIGATION
import com.vons.mvvm.adapter.SYSTEM
import com.vons.mvvm.adapter.TabVpAdapter
import com.vons.mvvm.base.BaseFragment
import com.vons.mvvm.databinding.FragmentSquareBinding


class SquareFragment : BaseFragment() {

    private lateinit var dataBinding: FragmentSquareBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentSquareBinding.inflate(inflater)
        initView()
        return dataBinding.root
    }

   private fun initView() {
        dataBinding.vpSquare.adapter = TabVpAdapter(this)
        TabLayoutMediator(dataBinding.tabLayout, dataBinding.vpSquare, true) { tab, position ->
            tab.text = getPageTitle(position)
        }.attach()
    }

    private fun getPageTitle(position: Int): String {
        return when (position) {
            SYSTEM -> {
                getString(R.string.system)
            }
            NAVIGATION -> {
                getString(R.string.navigation)
            }
            else -> {
                ""
            }
        }
    }
}