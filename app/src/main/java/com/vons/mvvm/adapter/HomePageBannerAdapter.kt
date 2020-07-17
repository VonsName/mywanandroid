package com.vons.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.vons.mvvm.R
import com.vons.mvvm.databinding.HomePageBannerItemBinding
import com.vons.mvvm.entity.HomePageBannerInfo
import com.vons.mvvm.frag.HomePageFragmentDirections
import com.youth.banner.adapter.BannerAdapter

class HomePageBannerAdapter(dataList: MutableList<HomePageBannerInfo>) :
    BannerAdapter<HomePageBannerInfo, HomePageBannerAdapter.HomePagerBannerViewHolder>(
        dataList
    ) {


    class HomePagerBannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dataBinding: HomePageBannerItemBinding = DataBindingUtil.bind(itemView)!!
    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): HomePagerBannerViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
            .inflate(R.layout.home_page_banner_item, parent, false)
        return HomePagerBannerViewHolder(itemView)
    }

    override fun onBindView(
        holder: HomePagerBannerViewHolder?,
        data: HomePageBannerInfo?,
        position: Int,
        size: Int
    ) {
        holder?.dataBinding?.bannerInfo = data
        holder?.dataBinding?.let { itemBind ->
            itemBind.homePageBannerItem.setOnClickListener {
                data?.let { itemData ->
                    val action =
                        HomePageFragmentDirections.actionHomePageFragmentToWebViewFragment(
                            itemData.url,
                            itemData.title
                        )
                    itemBind.homePageBannerItem.findNavController().navigate(action)
                }
            }
        }
    }
}