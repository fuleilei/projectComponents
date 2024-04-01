package com.mine.project.content.bean

import androidx.databinding.Bindable
import com.mine.project.content.BR
import com.mine.project.content.base.BaseBean

class UserBean(name:String, password:String): BaseBean(){
    @Bindable
    var name = ""
    var phoneList:MutableList<PhoneBean> = mutableListOf()
    set(value) {
        field = value
        notifyPropertyChanged(BR.name)
    }
    @Bindable
    var password:String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }
//    constructor(name:String, password:String){
//        this.name = name
//        this.password = password
//    }
    init{
        this.name = name
        this.password = password
    }
}