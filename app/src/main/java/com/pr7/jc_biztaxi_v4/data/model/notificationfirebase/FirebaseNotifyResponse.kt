package com.pr7.jc_biztaxi_v4.data.model.notificationfirebase

data class FirebaseNotifyResponse(
    val clients: List<Client>,
    val feedback: Any,
    val from_direction: String,
    val price: Int,
    val seatorder_id: Int,
    val to_direction: String
)