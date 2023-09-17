package com.pr7.jc_yataxi_prv1.data.network


import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.pr7.jc_yataxi_prv1.utils.CONTEXT
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
//    var okHttpClient = OkHttpClient().newBuilder()
//        .connectTimeout(100, TimeUnit.SECONDS)
//        .readTimeout(100, TimeUnit.SECONDS)
//        .writeTimeout(100, TimeUnit.SECONDS)
//        .build()!!

    var okHttpClient: OkHttpClient =OkHttpClient().newBuilder()
        .addInterceptor(ChuckerInterceptor(CONTEXT))
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://yataxi.testing.uz/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val api:Api= retrofit.create(Api::class.java)
}