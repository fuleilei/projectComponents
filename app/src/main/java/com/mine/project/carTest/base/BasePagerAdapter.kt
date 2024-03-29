package com.mine.project.carTest.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mine.project.carTest.fragment.HomeFragment

class BasePagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    var listFragment:ArrayList<Fragment> = ArrayList()
    override fun getItemCount(): Int {
        return listFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return listFragment.get(position)
    }

    fun addFragment(fragment: Fragment){
        listFragment.add(fragment)
    }
}