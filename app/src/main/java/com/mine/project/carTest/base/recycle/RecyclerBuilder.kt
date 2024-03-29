package com.mine.project.carTest.base.recycle

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.mine.project.carTest.base.BaseBean
import java.util.*

/**
 * Created by ASIA on 2017/9/7.
 */
abstract class RecyclerBuilder {
    var dividerHeight = 0
    var urlId: String? = null
    var map: MutableMap<String, String>? = null
    var adapter: BaseQuickAdapter<Any, BaseViewHolder>? = null
    var nullBg = 0
    var fixedSize = false
    var firstSpace = false
    fun builder(): RecyclerBuilder {
        dividerHeight = setDividerHeight()
        urlId = setUrl()
        map = setMap()
        adapter = setAdapter()
        nullBg = setNullBg()
        fixedSize = setFixedSize()
        firstSpace = setFirstSpace()
        return this
    }

    /**
     * @return 首行上面是否设置空白间距
     */
    protected abstract fun setFirstSpace(): Boolean

    /**
     * 设置 列表间距
     */
    abstract fun setDividerHeight(): Int

    /**
     * Item高度是否不变
     */
    abstract fun setFixedSize(): Boolean

    /**
     * 访问地址
     */
    abstract fun setUrl(): String?

    /**
     * 调用数据
     */
    abstract fun setMap(): MutableMap<String, String>?

    /**
     * adapter
     */
    abstract fun setAdapter(): BaseQuickAdapter<Any, BaseViewHolder>?

    /**
     * 为空时候icon
     */
    abstract fun setNullBg(): Int //    /**
    //     * 返回数据String需要转到的Bean Type类型
    //     *
    //     * @return
    //     */
    //    public abstract Type needType();
}