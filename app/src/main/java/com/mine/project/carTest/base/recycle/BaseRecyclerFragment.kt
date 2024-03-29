package com.mine.project.carTest.base.recycle

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.mine.project.carTest.R
import com.mine.project.carTest.base.BaseBean
import com.mine.project.carTest.base.BaseFragment
import com.mine.project.carTest.bean.UserBean
import com.mine.project.carTest.databinding.BaseFragmentLayoutBinding
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import kotlinx.coroutines.launch


abstract class BaseRecyclerFragment<T> : BaseFragment<BaseFragmentLayoutBinding>() {
    val PAGE_NUM = 20
    val SCROLL_REFRESH = 0
    val SCROLL_LOADMORE = 1
    var mRecycler: RecyclerView? = null
    var builder: RecyclerBuilder? = null
    lateinit var refreshLayout: SmartRefreshLayout
    var currentScrollState = 0
    val maxSize = 10
    var currentPage = 1
    var hasNextPage = true
    var listLiveInfo = mutableListOf<BaseBean>()
    override fun getLayoutId(): Int {
        return R.layout.base_fragment_layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initRecycler()
        loading()
    }

    fun initView(){
        refreshLayout = binding.refreshLayout
        initRefresh()
        builder = getRecyclerBuilder()
        mRecycler = binding.recylerview
    }

    open fun initRecycler() {
        initLayoutManager()
        mRecycler!!.setHasFixedSize(builder!!.fixedSize)
//        if (isLine()) {
//            mRecycler!!.addItemDecoration(RecycleViewDivider(context))
//        } else {
//            mRecycler!!.addItemDecoration(
//                SpaceItemDecoration(
//                    builder!!.dividerHeight,
//                    builder!!.firstSpace
//                )
//            )
//        }
        mRecycler!!.adapter = builder!!.adapter
        builder!!.adapter!!.removeEmptyView()
//        builder!!.adapter.setEmptyView(getEmptyView())
    }

   protected abstract fun initListData():MutableList<BaseBean>

    protected open fun initLayoutManager() {
        mRecycler!!.layoutManager = LinearLayoutManagerWrapper(activity)
    }

    fun initRefresh(){
//        binding.refreshLayout.setRefreshHeader(ClassicsHeader(activity))
//        binding.refreshLayout.setRefreshFooter(ClassicsFooter(activity))
        refreshLayout.setOnRefreshListener(OnRefreshListener { refreshlayout ->
               loading()
        })
        refreshLayout.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore(refreshlayout: RefreshLayout) {
                getMoreData()
            }
        })
    }

    /**
     * 配置信息（间距，访问地址，参数，默认图片）
     *
     * @return
     */
    protected abstract fun getRecyclerBuilder(): RecyclerBuilder?


    /**
     * 重头刷新
     */
    open fun loading() {
//        if (mEmptyView == null || Tools.isDestroyed(activity)) {
//            return
//        }
        clearMoreInfo()
        currentPage = 1
        currentScrollState = SCROLL_REFRESH
        if (listLiveInfo.size == 0) {
//            mEmptyView.showLoading("正在获取数据")
        }
        getList(currentPage)
    }

    protected open fun clearMoreInfo() {
        hasNextPage = true
        refreshLayout.resetNoMoreData()
    }

    open fun getMoreData() {
        if (!hasNextPage) {
            onResponseOver()
            return
        }
        currentScrollState = SCROLL_LOADMORE
        currentPage = currentPage + 1
        getList(currentPage)
    }

    open fun getList(page: Int) {
//        if (mFragmentCall != null) {
//            mFragmentCall.cancel()
//        }
        currentPage = page
        if (builder == null || builder!!.setMap() == null) {
            return
        }
//        val map: MutableMap<String, String>? = builder!!.setMap()
//        map!!["currentPage"] = currentPage.toString() + ""
//        map!!["pageSize"] = initPageNum().toString() + ""
//        EasyHttp.post(builder!!.urlId)
//            .addInterceptor(GlobalData.getHeadParam(context, map))
//            .upJson(trunJson(map))
//            .cacheKey(javaClass.simpleName)
//            .execute(initSimpleCallBack())
        lifecycleScope.launch{
//            onResponseSuccess(initListData(),"")
             requestNet()
        }
    }

    open fun onResponseOver() {
//        if (mEmptyView == null || Tools.isDestroyed(activity)) {
//            return
//        }
        if (isAdded && refreshLayout != null) {
            if (currentScrollState == SCROLL_REFRESH) {
                refreshLayout.finishRefresh()
            } else {
                if (hasNextPage) {
                    refreshLayout.finishLoadMore()
                } else {
                    refreshLayout.finishLoadMoreWithNoMoreData()
                }
            }
        }
    }

    open fun isLine(): Boolean {
        return false
    }

    protected open fun initPageNum(): Int {
        return PAGE_NUM
    }

    open fun onResponseSuccess(gsonObject: List<UserBean>, text: String) {
        onResponseOver()
//        if (mEmptyView == null || Tools.isDestroyed(activity)) {
//            return
//        }
        if ((gsonObject == null || gsonObject.size == 0) && isFirstPage()) {
//            mEmptyView.showEmptyPage(text, btnStr, builder!!.nullBg, cornerBtnClick)
            return
        }
        if (gsonObject == null) {
            return
        }
        if (isFirstPage()){
            listLiveInfo.clear()
        }
        setHasNextPage(gsonObject.size)
        listLiveInfo.addAll(gsonObject)
        builder?.adapter?.setList(listLiveInfo)
//        builder?.adapter?.notifyDataSetChanged()
//        if (listLiveInfo != null && listLiveInfo.size == 0) {
//            mEmptyView.showEmptyPage(text, null, builder!!.nullBg, cornerBtnClick)
//        } else {
//            mEmptyView.setVisibility(View.GONE)
//        }
    }

    open fun setHasNextPage(size: Int) {
        hasNextPage = size > 0
    }

    fun isFirstPage():Boolean{
        return currentPage==1
    }


    abstract fun requestNet()


}