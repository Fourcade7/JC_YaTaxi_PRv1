package com.pr7.jc_yataxi_prv1.data.model.newdirection.response

data class Seat(
    val client: Any,
    val id: Int,
    val is_free: Boolean,
    val price: Int,
    val seat: SeatX
)