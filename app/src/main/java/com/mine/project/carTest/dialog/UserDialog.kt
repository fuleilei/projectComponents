package com.mine.project.carTest.dialog

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.mine.project.carTest.R
import com.mine.project.carTest.base.BaseDialog
import com.mine.project.carTest.bean.UserBean
import com.mine.project.carTest.databinding.DialogUserViewBinding

class UserDialog() : BaseDialog<DialogUserViewBinding>() {
    var userBean: UserBean? = null
    override fun getLayoutId(): Int {
        return R.layout.dialog_user_view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            userBean = getSerializable("UserBean") as UserBean?
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(userBean!=null){
            binding.user = userBean
            binding.tvDescDialog.text = userBean?.password
        }
        binding.btRightDialog.setOnClickListener {
            mRightMethod?.run {
                dismiss()
                invoke()
            }
        }
    }
    fun setUserBean(userBean: UserBean): UserDialog {
        arguments?.putSerializable("UserBean", userBean)
        return this
    }
}