package com.pr7.jc_biztaxi_v4.data.model.newseat

data class NewSeatResponse(
    val client: Client,
    val direction: Int,
    val feedback: Any,
    val free_seats: List<FreeSeat>,
    val id: Int,
    val price: Int,
    val status: String
)