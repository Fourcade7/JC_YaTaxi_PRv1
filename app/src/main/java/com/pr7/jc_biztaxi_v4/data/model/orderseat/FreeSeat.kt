package com.pr7.jc_biztaxi_v4.data.model.orderseat

data class FreeSeat(
    val id: Int,
    val is_free: Boolean,
    val seat: Seat
)