package com.vons.mvvm.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vons.mvvm.HomePageViewModel
import com.vons.mvvm.adapter.HomePageBannerAdapter
import com.vons.mvvm.adapter.HomePageResultAdapter
import com.vons.mvvm.base.BaseFragment
import com.vons.mvvm.databinding.FragmentHomePageBinding
import com.youth.banner.indicator.CircleIndicator


class HomePageFragment : BaseFragment() {
    private val viewModel by viewModels<HomePageViewModel>()

    //    private val viewModel1 by navGraphViewModels<HomePageViewModel>(R.navigation.home_page_navigation)
    private lateinit var fragmentHomePageBinding: FragmentHomePageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomePageBinding = FragmentHomePageBinding.inflate(inflater)
        initData()
        return fragmentHomePageBinding.root
    }

    private fun initData() {
        val homePageResultAdapter = HomePageResultAdapter()
        fragmentHomePageBinding.rvHomePageInfo.adapter = homePageResultAdapter
        fragmentHomePageBinding.rvHomePageInfo.layoutManager = LinearLayoutManager(requireContext())
        requestHomePageData()
        viewModel.getHomePageInfo().observe(viewLifecycleOwner, Observer {
            homePageResultAdapter.submitData(lifecycle, it)
        })

        requestBanner()
        val homePageBannerAdapter = HomePageBannerAdapter(ArrayList())
        fragmentHomePageBinding.banner
            .addBannerLifecycleObserver(viewLifecycleOwner)
            .setAdapter(homePageBannerAdapter)
            .setIndicator(CircleIndicator(requireContext()))
            .start()
        viewModel.bannerLiveData.observe(viewLifecycleOwner, Observer {
            homePageBannerAdapter.setDatas(it)
            homePageBannerAdapter.notifyDataSetChanged()
        })
    }

    private fun requestHomePageData() {
        viewModel.getHomePageInfo()
    }

    private fun requestBanner() {
        viewModel.getHomePageBanner()
    }

    override fun onStop() {
        super.onStop()
        fragmentHomePageBinding.banner.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentHomePageBinding.banner.destroy()
    }
}