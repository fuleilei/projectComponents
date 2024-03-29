package com.mine.project.carTest.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.gyf.immersionbar.ImmersionBar
import com.mine.project.carTest.R

abstract class BaseActivity<V : ViewDataBinding>: AppCompatActivity(){
    protected lateinit var binding: V
    lateinit var contextActivity: Context
    var layoutId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .autoDarkModeEnable(true)
            .init()
    }

    protected fun initContentView(id:Int){
        layoutId = id
        binding = DataBindingUtil.setContentView(this, layoutId)
        contextActivity = this
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}