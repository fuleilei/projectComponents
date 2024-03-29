package com.mine.project.carTest.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.mine.project.carTest.R
import com.mine.project.carTest.bean.UserBean

class UserAdapter : BaseQuickAdapter<UserBean, BaseViewHolder> {

    constructor() : super(R.layout.adapter_item_user) {
    }


    override fun convert(holder: BaseViewHolder, item: UserBean) {
        holder.setText(R.id.tv_name,item.name)
        holder.setText(R.id.tv_password,item.password)
    }
}