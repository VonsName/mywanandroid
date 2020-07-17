package com.vons.mvvm.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vons.mvvm.OfficialAccountViewModel
import com.vons.mvvm.adapter.RvOfficialContentAdapter
import com.vons.mvvm.base.BaseFragment
import com.vons.mvvm.databinding.FragmentOfficialAccountTitleBinding

class OfficialAccountTitleFragment : BaseFragment() {

    private val viewModel by activityViewModels<OfficialAccountViewModel>()
    private lateinit var dataBinding: FragmentOfficialAccountTitleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentOfficialAccountTitleBinding.inflate(inflater)
        initView()
        return dataBinding.root
    }

    private fun initView() {
        dataBinding.rvOfficialContent.layoutManager = LinearLayoutManager(requireContext())
        dataBinding.rvOfficialContent.adapter = viewModel.officialContentAdapter!!
    }
}