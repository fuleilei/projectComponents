package com.mine.project.content.activity

import android.os.Bundle
import com.mine.project.content.R
import com.mine.project.content.base.BaseActivity
import com.mine.project.content.base.BasePagerAdapter
import com.mine.project.content.bean.UserBean
import com.mine.project.content.databinding.ActivityPageViewBinding
import com.mine.project.content.fragment.HomeFragment
import com.mine.project.content.fragment.UserListFragment


class PageActivity : BaseActivity<ActivityPageViewBinding>() {
    private lateinit var mPagerAdapter: BasePagerAdapter
    private val oneFragment by lazy { UserListFragment() }
    private val towFragment by lazy { HomeFragment(UserBean("fulei","1111")) }
    private val thirdFragment by lazy { UserListFragment() }
    private val fourFragment by lazy { UserListFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView(R.layout.activity_page_view)
        initViewPager()
    }


    private fun initViewPager() {
        mPagerAdapter = BasePagerAdapter(this)
        mPagerAdapter.addFragment(oneFragment)
        mPagerAdapter.addFragment(towFragment)
        mPagerAdapter.addFragment(thirdFragment)
        mPagerAdapter.addFragment(fourFragment)
        binding.userViewPager.adapter = mPagerAdapter
        binding.userViewPager.offscreenPageLimit = 4
        binding.userViewPager.setUserInputEnabled(false)
        //无需编写适配器，一行代码关联TabLayout与ViewPager
        val listStr = mutableListOf("首页","汽车","展示","我的")
        val unSelects = mutableListOf(R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher)
        val selects = mutableListOf(R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher)

        binding.stlTab.initTabData(listStr,unSelects,selects)
        binding.stlTab.bindViewPage(binding.userViewPager)

    }


}