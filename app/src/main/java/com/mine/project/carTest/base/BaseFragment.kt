package com.mine.project.carTest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.mine.project.carTest.R

abstract class BaseFragment<V:ViewDataBinding> :Fragment() {
    protected lateinit var binding: V
    protected  var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (null == rootView) {
            binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
            rootView = binding.root
        }

        return rootView
    }

    abstract fun getLayoutId():Int

    init {
        arguments = Bundle()
    }
}