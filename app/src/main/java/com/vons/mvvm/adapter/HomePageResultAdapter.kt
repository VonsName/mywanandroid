package com.vons.mvvm.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vons.mvvm.R
import com.vons.mvvm.databinding.HomePageItemBinding
import com.vons.mvvm.entity.ArticleInfo
import com.vons.mvvm.frag.HomePageFragmentDirections
import com.vons.mvvm.util.DateUtil
import com.vons.mvvm.util.fromJson
import com.vons.mvvm.util.toJson
import kotlinx.coroutines.Dispatchers

class HomePageResultAdapter :
    PagingDataAdapter<ArticleInfo, HomePageResultAdapter.HomePageResultViewHolder>(
        diffCallback = DiffBack,
        workerDispatcher = Dispatchers.IO
    ) {

    object DiffBack : DiffUtil.ItemCallback<ArticleInfo>() {
        override fun areItemsTheSame(oldItem: ArticleInfo, newItem: ArticleInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ArticleInfo, newItem: ArticleInfo): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class HomePageResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemDataBinding: HomePageItemBinding = DataBindingUtil.bind(itemView)!!
    }

    override fun onBindViewHolder(holder: HomePageResultViewHolder, position: Int) {
        val itemDataBinding = holder.itemDataBinding
        val item = getItem(position)
        item?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                it.publishTimeStr = DateUtil.timeStamp2LocalDateTimeString(it.publishTime)
            }
        }
//        val toJson = item.toJson()
//        val data = toJson.fromJson<ArticleInfo>()
        itemDataBinding.article = item
        itemDataBinding.homePageItem.setOnClickListener {
            item?.let {
                val action = HomePageFragmentDirections.actionHomePageFragmentToWebViewFragment(
                    it.link,
                    it.title
                )
                itemDataBinding.homePageItem.findNavController().navigate(action)
            }
        }
//        itemDataBinding.tvAuthor.text = item?.author
//        itemDataBinding.tvPushTime.text = item?.publishTime.toString()
//        itemDataBinding.tvTitle.text = item?.title
//        itemDataBinding.tvType.text = item?.chapterName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageResultViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.home_page_item, parent, false)
        return HomePageResultViewHolder(itemView)
    }
}