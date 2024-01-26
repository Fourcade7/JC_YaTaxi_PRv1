package com.pr7.jc_biztaxi_v4.data.model.orderseat

data class OrderSeatResponse(
    val client: Client,
    val direction: Int,
    val feedback: Any,
    val free_seats: List<FreeSeat>,
    val id: Int,
    val price: Int,
    val status: String
)