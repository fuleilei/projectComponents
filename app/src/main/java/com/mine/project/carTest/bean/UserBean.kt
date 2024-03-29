package com.mine.project.carTest.bean

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.mine.project.carTest.BR
import com.mine.project.carTest.base.BaseBean
import java.io.Serializable

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