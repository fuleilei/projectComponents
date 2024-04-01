package com.mine.project.content.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.gson.reflect.TypeToken
import com.mine.project.content.R
import com.mine.project.content.base.BaseFragment
import com.mine.project.content.bean.ContactsInfo
import com.mine.project.content.bean.UserBean
import com.mine.project.content.databinding.FragmentHomeBinding
import com.mine.project.content.net.HttpRequest
import com.mine.project.content.net.ReturnBean
import com.mine.project.content.sql.ContactsDatabase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment() : BaseFragment<FragmentHomeBinding>() {
    var userBean: UserBean? = null
    var dataBaseSql: ContactsDatabase? = null
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    constructor(userBean: UserBean) : this() {
        arguments?.putSerializable("UserBean", userBean)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            userBean = getSerializable("UserBean") as UserBean?
        }
        dataBaseSql = ContactsDatabase(context)
        createSql()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvName.post { binding.tvName.text = userBean?.name }
        val whereStr = " where cid=1 "
//        val infoList: List<ContactsInfo> = dataBaseSql?.query(whereStr) as List<ContactsInfo>
        val info  = dataBaseSql?.queryContactsInfo(whereStr)
        binding.lyHeadView.tvTile?.text = info?.name
        binding.btnChange.setOnClickListener {
//            dataBaseSql?.update(info)
        }
//        binding.lyHeadView.setHeadColor(R.color.line_color)

    }

    fun createSql() {
        lifecycleScope.launch {
            for(i in 0..10){
                var info: ContactsInfo = ContactsInfo()
                info.name = "开始" + System.currentTimeMillis()+i
                info.phone = "phone:100" +i
                dataBaseSql?.insert(info)
                delay(5)
            }
            Toast.makeText(activity,"写入",Toast.LENGTH_SHORT).show()

        }
    }

    fun initRetrofit() {
        val listType = object : TypeToken<ReturnBean<List<UserBean>>>() {}.type
        HttpRequest.getRequest("get_data.json",listType, object : HttpRequest.RequestListener<List<UserBean>>{
            override fun success(data: List<UserBean>, msg: String) {
                Toast.makeText(activity,data.get(0).name,Toast.LENGTH_LONG).show()
            }

            override fun error(code: Int, msg: String) {
                Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show()
            }

            override fun failNet(code: Int, msg: String) {
                Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show()
            }

        })

    }

    fun success(list:List<UserBean>, msg: String){

    }
}