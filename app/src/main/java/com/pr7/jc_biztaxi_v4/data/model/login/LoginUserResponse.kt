package com.pr7.jc_biztaxi_v4.data.model.login

data class LoginUserResponse constructor(
    val otp: String?=null,
    val message: String?=null,
    val phone:ArrayList<String> ? = null
)