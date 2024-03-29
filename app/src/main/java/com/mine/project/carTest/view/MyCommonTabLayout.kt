package com.mine.project.carTest.view

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager2.widget.ViewPager2
import com.flyco.tablayout.CommonTabLayout
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import java.util.ArrayList

class MyCommonTabLayout : CommonTabLayout {
    lateinit var viewPager: ViewPager2

    constructor(context: Context) : super(context,null,0) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(
        context,
        attrs,
        0
    )

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun bindViewPage(viewPager: ViewPager2){
        this.viewPager = viewPager
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentTab = position
            }
        })
        setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                viewPager.setCurrentItem(position,false)
            }

            override fun onTabReselect(position: Int) {

            }

        })
    }

    fun initTabData(listTabStr:MutableList<String>){
        var listTab: ArrayList<CustomTabEntity> = ArrayList()
        for(str in listTabStr){
            listTab.add(BaseTabEntity(str))
        }
        setTabData(listTab)
    }

    fun initTabData(listTabStr:MutableList<String>,unSelects:MutableList<Int>,selects:MutableList<Int>){
        var listTab: ArrayList<CustomTabEntity> = ArrayList()
        for(str in listTabStr){
            val index = listTabStr.indexOf(str)
            listTab.add(BaseTabEntity(str,unSelects.get(index),selects.get(index)))
        }
        setTabData(listTab)
    }


}