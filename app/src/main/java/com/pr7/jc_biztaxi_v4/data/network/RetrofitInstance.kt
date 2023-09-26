package com.pr7.jc_biztaxi_v4.data.network


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitInstance {

//    var okHttpClient = OkHttpClient().newBuilder()
//        .connectTimeout(100, TimeUnit.SECONDS)
//        .readTimeout(100, TimeUnit.SECONDS)
//        .writeTimeout(100, TimeUnit.SECONDS)
//        .build()!!

    var okHttpClient: OkHttpClient =OkHttpClient().newBuilder()
        //.addInterceptor(ChuckerInterceptor(CONTEXT))
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .writeTimeout(100, TimeUnit.SECONDS)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://yataxi.testing.uz/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val api:Api= retrofit.create(Api::class.java)
}