package com.vons.mvvm.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vons.mvvm.SquareFragmentViewModel
import com.vons.mvvm.adapter.NavigationItemAdapter
import com.vons.mvvm.base.BaseFragment
import com.vons.mvvm.databinding.FragmentNavigationBinding


class NavigationFragment : BaseFragment() {

    private lateinit var dataBinding: FragmentNavigationBinding
    private val viewModel: SquareFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentNavigationBinding.inflate(inflater)
        initView()
        return dataBinding.root
    }

    private fun initView() {
        val navigationItemAdapter = NavigationItemAdapter()
        dataBinding.rvNavigation.adapter = navigationItemAdapter
        dataBinding.rvNavigation.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getNavigationData()
        viewModel.navigationLiveData.observe(viewLifecycleOwner, Observer {
            navigationItemAdapter.submitData(it)
        })
    }
}