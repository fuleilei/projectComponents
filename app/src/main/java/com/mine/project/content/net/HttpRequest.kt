package com.mine.project.content.net

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


object HttpRequest {
    val DEFAULT_TIMEOUT:Long = 5
    val CODE_TIMEOUT:Int = 1111
    val baseUrl = "https://www.baidu.com"
//    val baseUrl = "https://petstore-demo.apifox.com"
    var retrofit: Retrofit? = null
    var appService: HttpService? = null
    val gson = Gson()

    var successMethod: (() -> Unit)? = null

    init {
        //retrofit底层用的okHttp,所以设置超时还需要okHttp
        //然后设置5秒超时
        //其中DEFAULT_TIMEOUT是我这边定义的一个常量
        //TimeUnit为java.util.concurrent包下的时间单位
        //TimeUnit.SECONDS这里为秒的单位
        val client:OkHttpClient =  OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build();
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        appService = retrofit?.create(HttpService::class.java)
    }

    /**
     * post请求
     */

    fun <T> postRequest(path: String,map:Map<String,String>?, type: Type,listener:RequestListener<T>) {
        var json = ""
        if(map!=null){
            json = gson.toJson(map)
        }
        appService?.postData(path,json)?.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                val jsonStr1 = response.body()
                val jsonStr = "{\"code\":0,\"content\":[{\"name\":\"开始17017558563950\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558564661\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558564792\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558564883\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558564964\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558565035\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558565126\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558565197\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558565278\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558565369\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始170175585654410\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]}],\"message\":\"\"}"
                requestOver<T>(jsonStr, type, listener)
            }

            override fun onFailure(call: Call<String>, wrapper: Throwable) {
                wrapper.printStackTrace()
                requestFail(wrapper, listener)
            }
        })
    }

    /**
     * get请求
     */
    fun <T> getRequest(path: String, type: Type,listener:RequestListener<T>) {
        appService?.getData(path)?.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                val jsonStr1 = response.body()
                val jsonStr = "{\"code\":0,\"content\":[{\"name\":\"开始17017558563950\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558564661\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558564792\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558564883\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558564964\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558565035\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558565126\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558565197\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558565278\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始17017558565369\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]},{\"name\":\"开始170175585654410\",\"password\":\"\",\"phoneList\":[{\"phone\":\"18701680748\"},{\"phone\":\"18701680746\"}]}],\"message\":\"\"}"
                requestOver<T>(jsonStr, type, listener)
            }

            override fun onFailure(call: Call<String>, wrapper: Throwable) {
                wrapper.printStackTrace()
                requestFail(wrapper, listener)
            }
        })
    }

    private fun <T> requestFail(wrapper: Throwable, listener: RequestListener<T>) {
        if ((wrapper is ConnectException
                    || wrapper is SocketTimeoutException
                    || wrapper is TimeoutException)
        ) { //如果超出重试次数也抛出错误，否则默认是会进入onCompleted:code=1111超时
            listener.failNet(CODE_TIMEOUT, "网络超时")
        } else {
            listener.failNet(CODE_TIMEOUT, "网络异常")
        }
    }

    private fun <T> requestOver(jsonStr: String, type: Type, listener: RequestListener<T>) {
        val returnBean = gson.fromJson<ReturnBean<T>>(jsonStr, type)

        when (returnBean.code) {
            0 -> returnBean.content?.let { listener.success(it, returnBean.message) }
            else -> {
                listener.error(returnBean.code, returnBean.message)
                errorHttp(returnBean.code, returnBean.message)
            }
        }
    }



    interface RequestListener<T>{
        fun success(data:T, msg: String)
        fun error(code: Int, msg: String)
        fun failNet(code: Int, msg: String)
    }

    fun errorHttp(code: Int,msg:String) {
        when (code) {
            101 -> {

            }
            102 -> {

            }
            else -> {

            }
        }
    }



}