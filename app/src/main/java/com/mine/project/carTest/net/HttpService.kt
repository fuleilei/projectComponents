package com.mine.project.carTest.net

import retrofit2.Call
import retrofit2.http.*

interface HttpService {

    @Headers("User-Agent: okhttp", "Cache-Control: max-age=0")
    @GET("{path}")
    fun getData(@Path("path")path:String): Call<String>


    @POST("/{path}")
    fun postData(@Path("path") path: String,@Body data:String): Call<String>



}