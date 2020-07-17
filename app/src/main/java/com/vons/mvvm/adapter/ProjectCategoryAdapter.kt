package com.vons.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vons.mvvm.ProjectCategoryViewModel
import com.vons.mvvm.R
import com.vons.mvvm.databinding.ProjectCategoryItemBinding
import com.vons.mvvm.entity.ProjectCategoryEntity

class ProjectCategoryAdapter(private val listener:ProjectContentItemClickListener) :
    RecyclerView.Adapter<ProjectCategoryAdapter.ProjectCategoryViewHolder>() {
    private lateinit var categoryViewHolder: ProjectCategoryViewHolder
    private var dataList: MutableList<ProjectCategoryEntity> = ArrayList()

    inner class ProjectCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dataBinding: ProjectCategoryItemBinding = DataBindingUtil.bind(itemView)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectCategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.project_category_item, parent, false)
        categoryViewHolder = ProjectCategoryViewHolder(itemView)
        return categoryViewHolder
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    override fun onBindViewHolder(holder: ProjectCategoryViewHolder, position: Int) {
        val item = dataList[position]
        holder.dataBinding.data = item
        holder.dataBinding.clProjectCategory.setOnClickListener {
            listener.onItemClick(item.id)
        }
        if (position == 0) {
            listener.onItemClick(item.id)
        }
    }

    fun submitDataList(dataList: List<ProjectCategoryEntity>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    interface ProjectContentItemClickListener {
        fun onItemClick(cid: Int)
    }
}