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
import com.vons.mvvm.databinding.OfficialContentItemBinding
import com.vons.mvvm.entity.ProjectContentEntity
import com.vons.mvvm.frag.OfficialAccountFragmentDirections

const val FORMAL_VIEW = 0
const val FOOTER_VIEW = 1

class RvOfficialContentAdapter :
    PagingDataAdapter<ProjectContentEntity, RvOfficialContentAdapter.RvOfficialContentViewHolder>(
        CallBack
    ) {
    object CallBack : DiffUtil.ItemCallback<ProjectContentEntity>() {
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

    class RvOfficialContentViewHolder(itemView: View, viewType: Int) :
        RecyclerView.ViewHolder(itemView) {
        lateinit var dataBinding: OfficialContentItemBinding

        init {
            if (viewType != FOOTER_VIEW) {
                dataBinding = DataBindingUtil.bind(itemView)!!
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvOfficialContentViewHolder {
        return if (viewType == FOOTER_VIEW) {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.footer_view, parent, false)
            RvOfficialContentViewHolder(itemView, viewType)
        } else {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.official_content_item, parent, false)
            RvOfficialContentViewHolder(itemView, viewType)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) FOOTER_VIEW else FORMAL_VIEW
    }

    override fun onBindViewHolder(holder: RvOfficialContentViewHolder, position: Int) {
        if (holder.itemViewType == FOOTER_VIEW) return
        val item = getItem(position)
        holder.dataBinding.data = item
        holder.dataBinding.clOfficialItem.setOnClickListener {
            item?.let {
                val action =
                    OfficialAccountFragmentDirections.actionOfficialAccountFragmentToWebViewFragment(
                        it.link,
                        it.title
                    )
                holder.dataBinding.clOfficialItem.findNavController().navigate(action)
            }
        }
    }
}