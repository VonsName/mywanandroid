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
import com.vons.mvvm.adapter.SystemItemAdapter
import com.vons.mvvm.base.BaseFragment
import com.vons.mvvm.databinding.FragmentSystemBinding

class SystemFragment : BaseFragment() {

    private val viewModel: SquareFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = FragmentSystemBinding.inflate(inflater)
        val systemItemAdapter = SystemItemAdapter()
        dataBinding.rvSystemItem.adapter = systemItemAdapter
        dataBinding.rvSystemItem.layoutManager = LinearLayoutManager(requireContext())
        viewModel.systemLiveData.observe(viewLifecycleOwner, Observer {
            systemItemAdapter.submitData(it)
        })
        viewModel.getSystemData()
        return dataBinding.root
    }
}