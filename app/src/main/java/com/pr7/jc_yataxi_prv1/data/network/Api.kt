package com.pr7.jc_yataxi_prv1.data.network

import com.pr7.jc_yataxi_prv1.data.model.register.RegisterUser
import com.pr7.jc_yataxi_prv1.data.model.register.RegisterUserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {


    //https://yataxi.testing.uz/en/users/api/register/
    @POST("en/users/api/registerUser/")
    suspend fun registerUser(@Body registerUser: RegisterUser):Response<RegisterUserResponse>
}