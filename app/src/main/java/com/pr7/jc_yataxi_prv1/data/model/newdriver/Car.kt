package com.pr7.jc_yataxi_prv1.data.model.newdriver

data class Car(
    val car_type: String?=null,
    val id: Int?=null,
    val name: String?=null,
    val seats: List<Seat>?=null,
)