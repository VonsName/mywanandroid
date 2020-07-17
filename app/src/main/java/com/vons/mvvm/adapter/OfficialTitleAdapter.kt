package com.vons.mvvm.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vons.mvvm.entity.SystemEntity
import com.vons.mvvm.frag.OfficialAccountTitleFragment

class OfficialTitleAdapter(titles: Map<Int, SystemEntity>, fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    private val fragmentCreators: MutableMap<Int, () -> Fragment> = mutableMapOf()

    init {
        for (i in titles.values.indices) {
            fragmentCreators[i] = { OfficialAccountTitleFragment() }
        }
    }

    override fun getItemCount(): Int {
        return fragmentCreators.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}