package com.pr7.jc_yataxi_prv1.data.model.newdirection.request

data class DirectionNew constructor(
    val start_date :String?=null,
    val end_date :String?=null,
    val price :Int?=null,
    val from_region :Int?=null,
    val from_district :Int?=null,
    val to_region :Int?=null,
    val to_district :Int?=null,

    )