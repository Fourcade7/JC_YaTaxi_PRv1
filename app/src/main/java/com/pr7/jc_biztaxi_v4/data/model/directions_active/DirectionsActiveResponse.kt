package com.pr7.jc_biztaxi_v4.data.model.directions_active

data class DirectionsActiveResponse(
    val active: Boolean,
    val air_conditioner: Boolean,
    val car: Car,
    val car_color: String,
    val car_number: String,
    val driver: Driver,
    val end_date: String,
    val finished: Boolean,
    val from_district: FromDistrict,
    val from_neighborhood: FromNeighborhood,
    val from_region: FromRegion,
    val fuel_type: String,
    val id: Int,
    val is_agreed: Boolean,
    val orders: List<Seats>?= emptyList(),
    val passengers: Int,
    val price: Int,
    val seats: List<Seat>,
    val start_date: String,
    val to_district: ToDistrict,
    val to_neighborhood: ToNeighborhood,
    val to_region: ToRegion
)