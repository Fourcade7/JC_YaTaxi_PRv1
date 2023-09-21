package com.pr7.jc_yataxi_prv1.data.model.newdriver

data class DriverCarRegisterResponse(
    val air_conditioner: Boolean?=true,
    val car: Car?=null,
    val car_color: String?=null,
    val car_number: String?=null,
    val fuel_type: String?=null,
    val id: Int?=null
)