package com.pr7.jc_biztaxi_v4.data.model.changeuserinfo

data class ChangeUserInfoResponse(
    val first_name: String?=null,
    val id: String?=null,
    val last_name: String?=null,
    val passport: String?=null,
    val phone: String?=null,
    val gender:String?=null,
    val two_step_password: Boolean?=null,
    val user_type: String?=null
)