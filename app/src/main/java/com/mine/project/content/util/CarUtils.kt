package com.mine.project.content.util

object CarUtils {
    fun checkList(list:MutableList<Int>, size:Int):MutableList<Int>{
        for(i in 0 until size){
            for (j in i+1 until size){
                if (list[i]>list[j]){
                    var stemp = list[i]
                    list[i] = list[j]
                    list[j] = stemp
                }
            }
        }
        return  list
    }
}