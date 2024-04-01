package com.mine.project.content.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

abstract class BaseDialog<V : ViewDataBinding> : DialogFragment() {
    protected lateinit var binding: V
    protected var rootView: View? = null
    protected var mCanceledOnTouchOutside = false
    private var mDismissMethodAdded = false

    companion object {
        const val BASE_DIALOG_TAG = "BASE_DIALOG"
        private var mFragment: FragmentManager? = null

        //        private var mBuildMethod: ((Dialog?, View?) -> Unit)? = null
        private var mDismissMethod: (() -> Unit)? = null

        //        private var mNegativeButtonMethod: ((Dialog?, View?) -> Unit)? = null
//        private var mPositiveButtonMethod: ((Dialog?, View?) -> Unit)? = null
        var mLeftMethod: (() -> Unit)? = null
        var mRightMethod: (() -> Unit)? = null


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (null == rootView) {
            binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
            rootView = binding.root
        }
        dialog?.run {
            setCanceledOnTouchOutside(mCanceledOnTouchOutside)
        }

        return rootView
    }

    init {
        arguments = Bundle()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            mCanceledOnTouchOutside = getBoolean("mCanceledOnTouchOutside")
            mDismissMethodAdded = getBoolean("mDismissMethodAdded")
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog ?: return
        val window = dialog.window ?: return
        val params = window.attributes
        // 设置背景色透明
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        // 设置Dialog动画效果
//        if (mAnimRes > 0) window.setWindowAnimations(mAnimRes)
//        // 设置Dialog的宽度
//        if (mWidth > 0) params.width = mWidth
//        // 设置Dialog的高度
//        if (mHeight > 0) params.height = mHeight
        // 设置屏幕透明度 0.0f~1.0f(完全透明~完全不透明)
        params.dimAmount = 0.4f
        params.gravity = Gravity.CENTER
        window.attributes = params
    }

    abstract fun getLayoutId(): Int

    fun setFragmentManager(fragment: FragmentManager): BaseDialog<*> {
        mFragment = fragment
        return this
    }

    fun setCanceledOnTouchOutside(canceledOnTouchOutside: Boolean): BaseDialog<*> {
        mCanceledOnTouchOutside = canceledOnTouchOutside
        arguments?.putBoolean("mCanceledOnTouchOutside", mCanceledOnTouchOutside)
        return this
    }

    fun setDismissMethod(dismissMethod: () -> Unit): BaseDialog<*> {
        mDismissMethod = dismissMethod
        arguments?.putBoolean("mDismissMethodAdded", true)
        return this
    }

    override fun onDestroy() {
        if (mDismissMethodAdded) {
            mDismissMethod?.run {
                invoke()
            }
        }
        super.onDestroy()
    }


    fun show() {
        mFragment?.let {
            val ft = it.beginTransaction()
            try {
                val cls: Class<*> = DialogFragment::class.java
                val mDismissed = cls.getDeclaredField("mDismissed")
                mDismissed.isAccessible = true
                mDismissed[this] = false
                val mShownByMe = cls.getDeclaredField("mShownByMe")
                mShownByMe.isAccessible = true
                mShownByMe[this] = true
            } catch (e: Exception) {
                super.show(ft, BASE_DIALOG_TAG)
                return
            }
            val prev = it.findFragmentByTag(BASE_DIALOG_TAG)
            if (prev != null) {
                ft.remove(prev)
            }
            ft.add(this, BASE_DIALOG_TAG)
            ft.commitAllowingStateLoss()
        }
    }

    fun setLeftBtnClick(leftBtnMethod: () -> Unit): BaseDialog<*> {
        mLeftMethod = leftBtnMethod
        return this
    }

    fun setRightBtnClick(rightBtnMethod: () -> Unit): BaseDialog<*> {
        mRightMethod = rightBtnMethod
        return this
    }

}