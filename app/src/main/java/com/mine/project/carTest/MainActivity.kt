package com.mine.project.carTest

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.mine.project.carTest.activity.PageActivity
import com.mine.project.carTest.base.BaseActivity
import com.mine.project.carTest.bean.UserBean
import com.mine.project.carTest.databinding.ActivityMainBinding
import com.mine.project.carTest.dialog.CommonDialog
import com.mine.project.carTest.dialog.UserDialog
import com.mine.project.carTest.net.HttpService
import com.mine.project.carTest.util.OperatorUse
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : BaseActivity<ActivityMainBinding>() {
    lateinit var userInfo: UserBean
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView(R.layout.activity_main)
        userInfo = UserBean("leavesCb", "123456")
        binding.user = userInfo
        listUser.add(userInfo)
        binding.btnChange.setOnClickListener {
            listUser[0].name = OperatorUse.set1.size.toString()
            listUser[0].password = OperatorUse.set1.toString()
            val intent = Intent(contextActivity, PageActivity::class.java)
            startActivity(intent)

//            showDialog()
        }
//        binding.btnChange.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?) {
//
//            }
//
//        })
        runBlocking {
            launch {

            }
        }
        mainScope.launch {
            var a = launch {

            }

            // 在主线程上获取数据
//            val data = fetchDataFromNetwork()  // 假设这是一个挂起函数，用于从网络加载数据
//            // 在主线程上更新 UI
//            textView.text = data
        }
        val job: Job = lifecycleScope.launch {
            delay(1000)
            suspend {
                listUser[0].name = "txaicao"
            }
            delay(3000)
            listUser[0].name = "flcaotx"
        }

    }

    lateinit var list: List<Int>
    var listUser = mutableListOf<UserBean>()


    override fun onDestroy() {
        super.onDestroy()
        // 当 Activity 销毁时，取消所有的协程以避免内存泄漏
        mainScope.cancel()
    }

    fun showDialog() {
        CommonDialog().setTitle("你好")
            .setContent("确定删除?")
            .setLeftStr("左边")
            .setRightStr("右边")
            .setLeftBtnClick {
//                 Toast.makeText(contextActivity, "zuobian", Toast.LENGTH_LONG).show()
            }.setRightBtnClick {
//            Toast.makeText(contextActivity, "youbian", Toast.LENGTH_LONG).show()
            }
            .setFragmentManager(supportFragmentManager)
            .setCanceledOnTouchOutside(true) // 点击dialog外部关闭dialog
            .setDismissMethod { // Dialog消失回调
//                Toast.makeText(contextActivity, "nihao", Toast.LENGTH_LONG).show()
            }.show()
        val user = UserBean("fulei", "123456")
        UserDialog().setUserBean(user).setFragmentManager(supportFragmentManager)
            .setCanceledOnTouchOutside(true) // 点击dialog外部关闭dialog
            .setRightBtnClick {
//            Toast.makeText(contextActivity, "youbian", Toast.LENGTH_LONG).show()
            }.setDismissMethod { // Dialog消失回调
//                Toast.makeText(contextActivity, "nihao", Toast.LENGTH_LONG).show()
            }.show()
    }


}