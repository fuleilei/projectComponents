package com.mine.project.content.activity

import android.os.Bundle
import com.mine.project.content.R
import com.mine.project.content.base.BaseActivity
import com.mine.project.content.databinding.ActivityFragmentMainBinding
import com.mine.project.content.fragment.UserListFragment

class FragmentActivity :BaseActivity<ActivityFragmentMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView(R.layout.activity_fragment_main)
        initFragment()
    }

    fun initFragment(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val one= UserListFragment()
// 判断不同的fragment
        fragmentTransaction.add(R.id.fragment_id,one)
//        fragmentTransaction.replace(R.id.fragment_id, one)
//        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit()
    }
}