package com.vons.mvvm.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vons.mvvm.ProjectCategoryViewModel
import com.vons.mvvm.adapter.ProjectCategoryAdapter
import com.vons.mvvm.adapter.ProjectContentAdapter
import com.vons.mvvm.base.BaseFragment
import com.vons.mvvm.databinding.FragmentProjectBinding

class ProjectFragment : BaseFragment(), ProjectCategoryAdapter.ProjectContentItemClickListener {

    private val viewModel: ProjectCategoryViewModel by viewModels()
    private var contentAdapter: ProjectContentAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProjectBinding.inflate(inflater)
        val layoutManager = LinearLayoutManager(requireContext()).apply {
            this.orientation = LinearLayoutManager.HORIZONTAL
        }
        val categoryAdapter = ProjectCategoryAdapter(this)
        binding.rvCategory.layoutManager = layoutManager
        binding.rvCategory.adapter = categoryAdapter
        viewModel.categoryLiveData.observe(viewLifecycleOwner, Observer {
            categoryAdapter.submitDataList(it)
        })

        contentAdapter = ProjectContentAdapter()
        binding.rvContent.adapter = contentAdapter
        binding.rvContent.layoutManager = LinearLayoutManager(requireContext())
//        viewModel.contentLiveData.observe(viewLifecycleOwner, Observer {
//            contentAdapter?.submitData(lifecycle, it)
//        })
        return binding.root
    }

    private fun requestData() {
        viewModel.getProjectCategory()
    }

    override fun onItemClick(cid: Int) {
        viewModel.getProjectContentByCid(cid).observe(viewLifecycleOwner, Observer {
            contentAdapter?.submitData(lifecycle, it)
        })
//        viewModel.getProjectContentByCid(cid)
    }
}