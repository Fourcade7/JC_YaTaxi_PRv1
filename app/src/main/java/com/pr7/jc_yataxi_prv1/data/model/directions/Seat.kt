package com.pr7.jc_yataxi_prv1

data class Seat(
    val client: String,
    val id: Int,
    val is_free: Boolean,
    val price: Int,
    val seat: SeatX
)