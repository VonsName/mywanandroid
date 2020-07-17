package com.vons.mvvm.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vons.mvvm.OfficialAccountViewModel
import com.vons.mvvm.adapter.OfficialTitleAdapter
import com.vons.mvvm.adapter.RvOfficialContentAdapter
import com.vons.mvvm.base.BaseFragment
import com.vons.mvvm.databinding.FragmentOfficialAccountBinding
import com.vons.mvvm.entity.SystemEntity


class OfficialAccountFragment : BaseFragment() {

    private val viewModel by activityViewModels<OfficialAccountViewModel>()
    private lateinit var dataBinding: FragmentOfficialAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentOfficialAccountBinding.inflate(inflater)
        initView()
        return dataBinding.root
    }

    private fun initView() {

        viewModel.officialAccount()
        viewModel.officialContentAdapter = RvOfficialContentAdapter()

        val data: MutableMap<Int, SystemEntity> = mutableMapOf()
        viewModel.officialAccountLiveData.observe(viewLifecycleOwner, Observer {
            for (i in it.indices) {
                data[i] = it[i]
            }
            dataBinding.vpTitle.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            dataBinding.vpTitle.adapter = OfficialTitleAdapter(data, this)
            TabLayoutMediator(dataBinding.tbTitle, dataBinding.vpTitle) { tab, position ->
                tab.text = data[position]?.name
            }.attach()
        })

        dataBinding.tbTitle.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.officialDataById(data[tab!!.position]!!.id).observe(viewLifecycleOwner,
                    Observer {
                        viewModel.officialContentAdapter?.submitData(lifecycle, it)
                    })
            }
        })

        dataBinding.swrOfficial.setOnRefreshListener {
            viewModel.officialAccount()
            dataBinding.swrOfficial.isRefreshing = false
        }
    }
}