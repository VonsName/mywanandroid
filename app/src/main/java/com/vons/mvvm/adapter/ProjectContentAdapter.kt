package com.vons.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vons.mvvm.R
import com.vons.mvvm.databinding.ProjectContentItemBinding
import com.vons.mvvm.entity.ProjectContentEntity
import com.vons.mvvm.frag.ProjectFragmentDirections

class ProjectContentAdapter :
    PagingDataAdapter<ProjectContentEntity, ProjectContentAdapter.ProjectContentViewHolder>(DiffBack) {
    object DiffBack : DiffUtil.ItemCallback<ProjectContentEntity>() {
        override fun areItemsTheSame(
            oldItem: ProjectContentEntity,
            newItem: ProjectContentEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProjectContentEntity,
            newItem: ProjectContentEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onBindViewHolder(holder: ProjectContentViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            it.dateAndAuthor = "${it.niceDate} | ${it.author}"
        }
        holder.dataBinding.data = item
        holder.dataBinding.clProjectContent.setOnClickListener {
            item?.let {
                val action =
                    ProjectFragmentDirections.actionProjectFragmentToWebViewFragment(
                        it.link,
                        it.title
                    )
                holder.dataBinding.clProjectContent.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectContentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.project_content_item, parent, false)
        return ProjectContentViewHolder(itemView)
    }

    inner class ProjectContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dataBinding: ProjectContentItemBinding = DataBindingUtil.bind(itemView)!!
    }
}

