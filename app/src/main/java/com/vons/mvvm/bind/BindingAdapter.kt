package com.vons.mvvm.bind

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vons.mvvm.R
import com.vons.mvvm.base.GlideRoundTransform

class BindingAdapter {
    companion object {
        @BindingAdapter(value = ["imgUrl", "error"], requireAll = false)
        @JvmStatic
        fun imageViewLoad(
            imageView: ImageView,
            imagePath: String,
            error: Drawable = ContextCompat.getDrawable(
                imageView.context,
                R.drawable.webchat
            )!!
        ) {
            normalLoad(imageView, imagePath, error)
        }

        private fun normalLoad(
            imageView: ImageView,
            imagePath: String,
            error: Drawable
        ) {
            Glide.with(imageView)
                .load(imagePath)
                .error(error)
                .placeholder(R.mipmap.internet_error)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }

        private fun loadRadius(
            imageView: ImageView,
            imagePath: String,
            error: Drawable, radius: Float
        ) {
            Glide.with(imageView)
                .load(imagePath)
                .error(error)
                .placeholder(R.mipmap.internet_error)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(GlideRoundTransform(radius))
                .into(imageView)
        }
    }
}

//@BindingAdapter(value = ["adapter"])
//inline fun <reified V : RecyclerView.ViewHolder> adapter(
//    recyclerView: RecyclerView,
//    adapter: RecyclerView.Adapter<V>
//) {
//    recyclerView.adapter = adapter
//}