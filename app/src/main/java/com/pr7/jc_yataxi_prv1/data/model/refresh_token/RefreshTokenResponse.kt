package com.pr7.jc_yataxi_prv1.data.model.refresh_token

data class RefreshTokenResponse(
    val access: String?=null,
    val refresh: String?=null,
    val detail: String?=null,
    val code: String?=null
)