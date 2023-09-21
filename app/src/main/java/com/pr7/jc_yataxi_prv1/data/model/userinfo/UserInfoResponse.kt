package com.pr7.jc_yataxi_prv1.data.model.userinfo

data class UserInfoResponse(
    val first_name: String?=null,
    val id: String?=null,
    val last_name: String?=null,
    val passport: String?=null,
    val phone: String?=null,
    val two_step_password: Boolean?=null,
    val user_type: String?=null,
    val code: String?=null

)