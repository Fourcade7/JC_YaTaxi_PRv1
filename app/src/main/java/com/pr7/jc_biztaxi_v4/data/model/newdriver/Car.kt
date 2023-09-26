package com.pr7.jc_biztaxi_v4.data.model.newdriver

data class Car(
    val car_type: String?=null,
    val id: Int?=null,
    val name: String?=null,
    val seats: List<Seat>?=null,
)