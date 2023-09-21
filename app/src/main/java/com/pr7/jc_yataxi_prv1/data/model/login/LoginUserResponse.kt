package com.pr7.jc_yataxi_prv1.data.model.login

data class LoginUserResponse constructor(
    val otp: String?=null,
    val message: String?=null,
    val phone:ArrayList<String> ? = null
)