package com.mine.project.carTest.view

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.mine.project.carTest.R
import com.mine.project.carTest.listener.Itemlistener
import com.mine.project.carTest.util.BaseUtils.dp2px

class HeaderViewLayout @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null) :
    RelativeLayout(context, attrs) {
    var tvBack: TextView? = null
    var tvTile: TextView? = null
    var tvRight: TextView? = null
    var headView: ViewGroup? = null
    var viewLine: View? = null
    private fun init() {
        val view = LayoutInflater.from(context).inflate(R.layout.base_head_view, this)
        tvBack = view.findViewById(R.id.tv_bar_left)
        tvTile = view.findViewById(R.id.tv_bar_title)
        tvRight = view.findViewById(R.id.tv_bar_right)
        viewLine = view.findViewById(R.id.head_line)
        headView = view.findViewById(R.id.layout_head_view)
    }

    fun singleTitle(title: String?) {
        tvBack!!.visibility = GONE
        tvTile!!.text = title
    }

    fun setTitle(title: String?) {
        tvTile!!.text = title
    }

    fun setHeadColor(colorId: Int) {
        headView!!.setBackgroundColor(resources.getColor(colorId))
    }

    fun setTvTileColor(colorId: Int) {
        tvTile!!.setTextColor(resources.getColor(colorId))
    }

    fun initHeadData(activity: Activity, title: String?) {
        tvBack!!.setOnClickListener { activity.finish() }
        tvTile!!.text = title
    }

    fun initHeadData(
        activity: Activity,
        title: String?,
        rightStr: String?,
        itemlistener: Itemlistener?
    ) {
        tvBack!!.setOnClickListener { activity.finish() }
        tvTile!!.text = title
        tvRight!!.visibility = VISIBLE
        tvRight!!.text = rightStr
        tvRight!!.setOnClickListener { v: View? -> itemlistener?.rightClick() }
    }

    fun initHeadData(
        activity: Activity,
        leftStr: String?,
        title: String?,
        rightStr: String?,
        itemlistener: Itemlistener?
    ) {
        tvBack!!.setOnClickListener { activity.finish() }
        tvBack!!.setCompoundDrawables(null, null, null, null)
        tvBack!!.text = leftStr
        tvTile!!.text = title
        tvRight!!.visibility = VISIBLE
        tvRight!!.text = rightStr
        tvRight!!.setOnClickListener { v: View? -> itemlistener?.rightClick() }
    }

    fun showLine(isShow: Boolean) {
        viewLine!!.visibility = if (isShow) VISIBLE else GONE
    }

    fun showBack(isShow: Boolean) {
        tvBack!!.visibility = if (isShow) VISIBLE else GONE
    }

    fun initRight(rightStr: String?, rightImg: Int, itemlistener: Itemlistener?, isWidth: Boolean) {
        tvRight!!.visibility = VISIBLE
        tvRight!!.text = rightStr
        if (rightImg != 0) {
            if (isWidth) {
                val leftDrawable = resources.getDrawable(rightImg)
                val width = dp2px(context, 25f)
                leftDrawable.setBounds(0, 0, width, width)
                tvRight!!.setCompoundDrawables(leftDrawable, null, null, null)
            } else {
                tvRight!!.setCompoundDrawablesWithIntrinsicBounds(
                    resources.getDrawable(rightImg),
                    null,
                    null,
                    null
                )
            }
        }
        tvRight!!.setOnClickListener { v: View? -> itemlistener?.rightClick() }
    }

    fun setRight(rightStr: String?, colorId: Int, sizeId: Int, itemlistener: Itemlistener?) {
        tvRight!!.visibility = VISIBLE
        tvRight!!.text = rightStr
        if (colorId > 0) {
            tvRight!!.setTextColor(ContextCompat.getColor(context, colorId))
        }
        if (sizeId > 0) {
            tvRight!!.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeId.toFloat())
        }
        tvRight!!.setOnClickListener { v: View? -> itemlistener?.rightClick() }
    }

    fun initRight(rightStr: String?, rightImg: Int, itemlistener: Itemlistener?, isWidth: Int) {
        tvRight!!.visibility = VISIBLE
        tvRight!!.text = rightStr
        if (rightImg != 0) {
            if (isWidth > 0) {
                val leftDrawable = resources.getDrawable(rightImg)
                val width = dp2px(context, isWidth.toFloat())
                leftDrawable.setBounds(0, 0, width, width)
                tvRight!!.setCompoundDrawables(leftDrawable, null, null, null)
            } else {
                tvRight!!.setCompoundDrawablesWithIntrinsicBounds(
                    resources.getDrawable(rightImg),
                    null,
                    null,
                    null
                )
            }
        }
        tvRight!!.setOnClickListener { v: View? -> itemlistener?.rightClick() }
    }

    fun setLeftImg(leftImg: Int) {
        if (leftImg != 0) {
            tvBack!!.setCompoundDrawablesWithIntrinsicBounds(
                resources.getDrawable(leftImg),
                null,
                null,
                null
            )
        }
    }

    fun initLeft(leftStr: String?, leftImg: Int, itemlistener: Itemlistener?) {
        tvBack!!.visibility = VISIBLE
        tvBack!!.text = leftStr
        if (leftImg != 0) {
            tvBack!!.setCompoundDrawablesWithIntrinsicBounds(
                resources.getDrawable(leftImg),
                null,
                null,
                null
            )
        }
        tvBack!!.setOnClickListener { v: View? -> itemlistener?.rightClick() }
    }

    init {
        init()
    }
}