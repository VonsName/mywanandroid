package com.vons.mvvm.base

import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class GlideRoundTransform(private val radius: Float) : CenterCrop() {

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        val transform = super.transform(pool, toTransform, outWidth, outHeight)
        return round(pool, transform)
    }

    private fun round(pool: BitmapPool, source: Bitmap): Bitmap {
        val result = pool.get(source.width, source.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)
        val paint = Paint()
        val shader = BitmapShader(
            source,
            Shader.TileMode.CLAMP,
            Shader.TileMode.CLAMP
        )
        paint.isAntiAlias = true
        paint.shader = shader
        val rectF = RectF(0F, 0F, source.width.toFloat(), source.height.toFloat())
        canvas.drawRoundRect(rectF, radius, radius, paint)
        canvas.setBitmap(null)
        return result
    }
}