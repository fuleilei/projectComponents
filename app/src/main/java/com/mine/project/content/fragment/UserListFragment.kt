package com.mine.project.content.fragment

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mine.project.content.adapter.UserAdapter
import com.mine.project.content.base.BaseBean
import com.mine.project.content.base.recycle.BaseRecyclerFragment
import com.mine.project.content.base.recycle.RecyclerBuilder
import com.mine.project.content.bean.ContactsInfo
import com.mine.project.content.bean.PhoneBean
import com.mine.project.content.bean.UserBean
import com.mine.project.content.net.HttpRequest
import com.mine.project.content.net.ReturnBean
import com.mine.project.content.sql.ContactsDatabase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserListFragment : BaseRecyclerFragment<UserBean>() {

    var dataBaseSql: ContactsDatabase? = null

    override fun getRecyclerBuilder(): RecyclerBuilder? {
        return object : RecyclerBuilder() {
            override fun setFirstSpace(): Boolean {
                return true
            }

            override fun setDividerHeight(): Int {
                return 0
            }
            override fun setFixedSize(): Boolean {
                return false
            }

            override fun setUrl(): String? {
                return ""
            }

            override fun setMap(): MutableMap<String, String>? {
                val hashMap = mutableMapOf<String, String>()
                return hashMap
            }

            override fun setAdapter(): BaseQuickAdapter<Any, BaseViewHolder>? {
                val adapter = UserAdapter() as BaseQuickAdapter<Any, BaseViewHolder>
                return adapter
            }

            override fun setNullBg(): Int {
                return 0
            }
        }.builder()
    }

    override fun initListData(): MutableList<BaseBean> {
        var userList = mutableListOf<UserBean>()
//        userList.add(UserBean("fl","111111"))
//        userList.add(UserBean("fl","123"))
//        userList.add(UserBean("fl","123"))
//        userList.add(UserBean("fl","123"))
//        userList.add(UserBean("fl","123"))
//        userList.add(UserBean("fl","123"))
//        userList.add(UserBean("fl","123"))
//        userList.add(UserBean("fl","123"))
//        userList.add(UserBean("fl","123"))
//        userList.add(UserBean("fl","222222"))
        if (dataBaseSql == null) {
            dataBaseSql = ContactsDatabase(context)
        }
        lifecycleScope.launch {

//            val whereStr = " where name like '%开始%'"
            val whereStr = ""
            val infoList: List<ContactsInfo> = dataBaseSql?.query(whereStr) as List<ContactsInfo>
            val count: Int = infoList.size
            if (count > 100) {
                val page:Int = count/100
                val num = count%100
                for (i in 1..page){
                    val size = i*100
                    val start = size -100
                   for (j in start until size){
                       if(j<infoList.size){
                           userList.add(UserBean(infoList.get(j).name, ""))
                       }
                   }
                    builder?.adapter?.notifyDataSetChanged()
                    delay(10)
                }
            }else{
                for(info in infoList){
                    val user = UserBean(info.name, "")
                    user.phoneList.add(PhoneBean("18701680748"))
                    user.phoneList.add(PhoneBean("18701680746"))
                    userList.add(user)
                }
            }
            val returnBean = ReturnBean<List<UserBean>>()
            returnBean.content = userList
            val gson = Gson()
            val jsonStr = gson.toJson(returnBean)
            println(jsonStr)


        }

        return userList as MutableList<BaseBean>
    }

    override fun requestNet() {
        val listType = object : TypeToken<ReturnBean<List<UserBean>>>() {}.type
        val map = mapOf("name" to "付","sex" to "nan")
        HttpRequest.postRequest("/pet/{2}",map,listType, object : HttpRequest.RequestListener<List<UserBean>>{
            override fun success(data: List<UserBean>, msg: String) {
                onResponseSuccess(data,"")
            }

            override fun error(code: Int, msg: String) {
                onResponseOver()
                Toast.makeText(activity,msg, Toast.LENGTH_SHORT).show()
            }

            override fun failNet(code: Int, msg: String) {
                onResponseOver()
                Toast.makeText(activity,msg, Toast.LENGTH_SHORT).show()
            }

        })
    }
}