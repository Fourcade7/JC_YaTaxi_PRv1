package com.pr7.jc_yataxi_prv1.data.model.otp

data class OTPUserResponse(
    val access: String?=null,
    val created: Boolean?=null,
    val id: String?=null,
    val refresh: String?=null,
    val message: String?=null,
    val code:ArrayList<String> ? = null
)