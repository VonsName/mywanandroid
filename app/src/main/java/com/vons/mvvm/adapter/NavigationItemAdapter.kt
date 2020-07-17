package com.vons.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vons.mvvm.R
import com.vons.mvvm.databinding.NavigationItemBinding
import com.vons.mvvm.entity.NavigationInfo
import com.vons.mvvm.entity.SystemEntity

class NavigationItemAdapter : RecyclerView.Adapter<NavigationItemAdapter.NavigationItemHolder>() {

    private val dataList: MutableList<NavigationInfo> = ArrayList()

    class NavigationItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dataBinding: NavigationItemBinding = DataBindingUtil.bind(itemView)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.navigation_item, parent, false)
        return NavigationItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: NavigationItemHolder, position: Int) {
        holder.dataBinding.data = dataList[position]
    }

    fun submitData(dataList: List<NavigationInfo>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }
}