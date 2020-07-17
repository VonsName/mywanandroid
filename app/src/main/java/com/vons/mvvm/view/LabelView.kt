package com.vons.mvvm.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class LabelView : ViewGroup {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var contentWidth = 0
        var maxItemHeight = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            contentWidth += child.measuredWidth
            if (i != childCount - 1) contentWidth += 16
            maxItemHeight = maxItemHeight.coerceAtLeast(child.measuredHeight)
        }

        setMeasuredDimension(
            measureSize(
                widthMeasureSpec,
                contentWidth + paddingLeft + paddingRight
            ), measureSize(heightMeasureSpec, maxItemHeight + paddingTop + paddingBottom)
        )
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {

    }

    private fun measureSize(measureSpec: Int, size: Int): Int {
        var result = 0
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = size
            if (specMode == MeasureSpec.AT_MOST) {
                result = result.coerceAtMost(specSize)
            }
        }
        result = result.coerceAtLeast(suggestedMinimumWidth)
        result = View.resolveSizeAndState(result, measureSpec, 0)
        return result
    }

}