package com.mine.project.carTest.dialog

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.mine.project.carTest.R
import com.mine.project.carTest.base.BaseDialog
import com.mine.project.carTest.databinding.DialogCommonViewBinding


class CommonDialog() : BaseDialog<DialogCommonViewBinding>() {
    protected var mTitle: String? = null
    protected var mContent: String? = null
    protected var mLeftStr: String? = null
    protected var mRgihtStr: String? = null

    override fun getLayoutId(): Int {
        return R.layout.dialog_common_view
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            mTitle = getString("mTitle")
            mContent = getString("mContent")
            mLeftStr = getString("mLeftStr")
            mRgihtStr = getString("mRgihtStr")
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val showTitle = !TextUtils.isEmpty(mTitle)
        val showContent = !TextUtils.isEmpty(mContent)
        if (showTitle) {
            binding.tvTitleDialog.text = mTitle
        }
        if (showContent) {
            binding.tvDescDialog.text = mContent
        }
        if(!TextUtils.isEmpty(mLeftStr)){
            binding.btLeftDialog.text = mLeftStr
        }
        if(!TextUtils.isEmpty(mRgihtStr)){
            binding.btRightDialog.text = mRgihtStr
        }
        binding.btLeftDialog.setOnClickListener {
            mLeftMethod?.run {
                dismiss()
                invoke()
            }
        }
        binding.btRightDialog.setOnClickListener {
            mRightMethod?.run {
                dismiss()
                invoke()
            }
        }
    }

    fun setTitle(title: String): CommonDialog {
        mTitle = title
        arguments?.putString("mTitle", mTitle)
        return this
    }

    fun setContent(content: String): CommonDialog {
        mContent = content
        arguments?.putString("mContent", mContent)
        return this
    }
    fun setLeftStr(leftStr: String): CommonDialog {
        mLeftStr = leftStr
        arguments?.putString("mLeftStr", mLeftStr)
        return this
    }
    fun setRightStr(rightStr: String): CommonDialog {
        mRgihtStr = rightStr
        arguments?.putString("mRgihtStr", mRgihtStr)
        return this
    }




}