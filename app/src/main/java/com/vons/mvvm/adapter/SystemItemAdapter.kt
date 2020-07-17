package com.vons.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vons.mvvm.R
import com.vons.mvvm.databinding.SystemItemBinding
import com.vons.mvvm.entity.SystemEntity

class SystemItemAdapter : RecyclerView.Adapter<SystemItemAdapter.SystemItemViewHolder>() {

    private val dataList: MutableList<SystemEntity> = ArrayList()

    class SystemItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dataBinding: SystemItemBinding = DataBindingUtil.bind(itemView)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SystemItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.system_item, parent, false)
        return SystemItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: SystemItemViewHolder, position: Int) {
        val item = dataList[position]
        holder.dataBinding.data = item
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        lp.marginStart = 10
        lp.marginEnd = 10
        item.children.forEach {
            val subText = TextView(holder.dataBinding.clSystemItem.context)
            subText.background = ContextCompat.getDrawable(
                holder.dataBinding.clSystemItem.context,
                R.drawable.system_item_selector
            )
            subText.text = it.name
            subText.layoutParams = lp
            holder.dataBinding.llSubItem.addView(subText)
        }
    }

    fun submitData(data: List<SystemEntity>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }
}