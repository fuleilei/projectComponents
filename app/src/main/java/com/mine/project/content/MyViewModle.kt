package com.mine.project.content

import androidx.lifecycle.ViewModel

class MyViewModle : ViewModel(){
    fun fetchData() {
//        viewModelScope.launch {
            // 在 ViewModel 的作用域中启动一个新的协程
            // 当 ViewModel 被清理时，这个协程会被自动取消
//            val data = fetchDataFromNetwork()  // 假设这是一个挂起函数，用于从网络加载数据
//            _data.value = data  // 更新 LiveData
//        }
    }
}