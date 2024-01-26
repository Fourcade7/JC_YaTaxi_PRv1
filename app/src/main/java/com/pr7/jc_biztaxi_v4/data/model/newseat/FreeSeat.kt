package com.pr7.jc_biztaxi_v4.data.model.newseat

data class FreeSeat(
    val id: Int,
    val is_free: Boolean,
    val seat: Seat
)