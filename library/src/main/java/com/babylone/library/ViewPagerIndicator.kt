package com.babylone.library

import android.content.Context
import android.content.res.TypedArray
import android.support.annotation.DrawableRes
import android.support.v4.view.ViewPager
import android.support.v4.view.ViewPager.SCROLL_STATE_SETTLING
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton


class ViewPagerIndicator @JvmOverloads constructor(
        context: Context,
        attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet), ViewPager.OnPageChangeListener {

    companion object {
        private const val THRESHOLD_OFFSET = 0.5
    }

    private var currentState: Int = 0
    private var currentPosition: Int = 0
    private var nextPosition: Int = 0
    private var indicatorMarginStart = 0
    private var indicatorMarginEnd = 0

    private var viewPager: ViewPager? = null

    @DrawableRes var tabSelector = R.drawable.indicator_selector

    init {
        orientation = HORIZONTAL

        attributeSet?.let {
            var typedArray: TypedArray? = null
            try {
                typedArray = context.obtainStyledAttributes(
                        attributeSet,
                        R.styleable.ViewPagerIndicator
                )
                indicatorMarginStart = typedArray.getDimension(
                        R.styleable.ViewPagerIndicator_start_margin,
                        resources.getDimension(R.dimen.indicator_gap_dp)).toInt()
                indicatorMarginEnd = typedArray.getDimension(
                        R.styleable.ViewPagerIndicator_end_margin,
                        resources.getDimension(R.dimen.indicator_gap_dp)).toInt()
            } finally {
                typedArray?.recycle()
            }
        }
    }

    fun setupWithViewPager(view: ViewPager?) {
        if (viewPager == view) {
            return
        }

        if (view?.adapter == null) {
            throw RuntimeException("ViewPager does not have adapter instance.")
        }

        viewPager?.removeOnPageChangeListener(this)
        viewPager = view
        viewPager?.let {
            it.addOnPageChangeListener(this)
            it.overScrollMode = View.OVER_SCROLL_NEVER
        }

        invalidate()
    }

    fun setupIndicatorSize(indicatorSize: Int) {
        if (viewPager == null) {
            throw RuntimeException("Indicator does not have ViewPager, execute " +
                    "setupWithViewPager(viewPager) before")
        }

        if (indicatorSize <= 1) {
            visibility = View.GONE
            return
        }

        removeAllViews()
        inflateViews(indicatorSize)
        setCheckedPosition(viewPager?.currentItem ?: 0 % indicatorSize)
        visibility = View.VISIBLE
    }

    private fun inflateViews(childCount: Int) {
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            marginStart = indicatorMarginStart
            marginEnd = indicatorMarginEnd
        }

        for (i in 0 until childCount) {
            RadioButton(context).apply {
                isClickable = false
                isChecked = false
                setButtonDrawable(android.R.color.transparent)
                setBackgroundResource(tabSelector)
                layoutParams = params
            }.run {
                addView(this)
            }
        }
    }

    private fun setCheckedPosition(position: Int) {
        for (index in 0..childCount) {
            val child = getChildAt(index) as? RadioButton
            val state = index == position % childCount
            child?.isChecked = state
        }
    }

    private fun updateCheckedPosition(position: Int) {
        if (position != nextPosition) {
            nextPosition = position
            setCheckedPosition(nextPosition)
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (currentState == SCROLL_STATE_SETTLING) {
            setCheckedPosition(currentPosition)
        } else {
            updateCheckedPosition(if (positionOffset > THRESHOLD_OFFSET) position + 1 else position)
        }
    }

    override fun onPageSelected(position: Int) {
        setCheckedPosition(position % childCount)
        currentPosition = position
        nextPosition = position
    }

    override fun onPageScrollStateChanged(state: Int) {
        currentState = state
    }

}