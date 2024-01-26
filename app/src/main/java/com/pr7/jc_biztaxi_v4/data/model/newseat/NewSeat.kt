package com.pr7.jc_biztaxi_v4.data.model.newseat

data class NewSeat(
    val `data`: List<DataSeat>,
    val direction: Int?=null,
    val free_seats: List<Int>
)