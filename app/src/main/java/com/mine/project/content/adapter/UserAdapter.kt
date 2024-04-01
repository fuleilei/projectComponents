package com.mine.project.content.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.mine.project.content.R
import com.mine.project.content.bean.UserBean

class UserAdapter : BaseQuickAdapter<UserBean, BaseViewHolder> {

    constructor() : super(R.layout.adapter_item_user) {
    }


    override fun convert(holder: BaseViewHolder, item: UserBean) {
        holder.setText(R.id.tv_name,item.name)
        holder.setText(R.id.tv_password,item.password)
    }
}