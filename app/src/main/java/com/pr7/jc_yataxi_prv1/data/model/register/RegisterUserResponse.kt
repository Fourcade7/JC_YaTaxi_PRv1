package com.pr7.jc_yataxi_prv1.data.model.register

data class RegisterUserResponse(
    val message: String?=null,
    val otp: String?=null,
    val phone:ArrayList<String> ? = null
)