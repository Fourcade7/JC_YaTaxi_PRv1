package com.pr7.jc_yataxi_prv1.data.model.newdriver

data class DriverCarRegister(
    val air_conditioner: Boolean=true,
    val car: Int,
    val car_color: String,
    val car_number: String,
    val fuel_type: String="methane_gas"
)