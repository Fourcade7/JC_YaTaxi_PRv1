package com.pr7.jc_biztaxi_v4.data.model.directions2

data class DirectionsR2(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: ArrayList<ResultData>?=null
)