package com.pr7.jc_biztaxi_v4.data.model.newdriver

data class DriverCarRegister(
    val air_conditioner: Boolean=true,
    val car: Int,
    val car_color: String,
    val car_number: String,
    val fuel_type: String="methane_gas"
)