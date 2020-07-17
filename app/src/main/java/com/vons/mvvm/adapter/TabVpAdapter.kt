package com.vons.mvvm.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vons.mvvm.frag.NavigationFragment
import com.vons.mvvm.frag.SystemFragment


const val SYSTEM = 0
const val NAVIGATION = 1

class TabVpAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentCreators: Map<Int, () -> Fragment> = mapOf(
        SYSTEM to { SystemFragment() },
        NAVIGATION to { NavigationFragment() }
    )

    override fun getItemCount(): Int {
        return fragmentCreators.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}