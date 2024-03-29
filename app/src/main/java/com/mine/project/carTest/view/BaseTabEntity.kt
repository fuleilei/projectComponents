package com.mine.project.carTest.view

import com.flyco.tablayout.listener.CustomTabEntity
import com.mine.project.carTest.R

class BaseTabEntity: CustomTabEntity {
    var title:String = ""
    var selectId:Int = 0
    var unSelectId:Int = 0
    constructor(title:String){
        this.title = title
    }

    constructor(title:String,unSelectId:Int,selectId:Int){
        this.title = title
        this.unSelectId = unSelectId
        this.selectId = selectId
    }

    override fun getTabTitle(): String {
        return title
    }

    override fun getTabSelectedIcon(): Int {
        return selectId
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectId
    }
}