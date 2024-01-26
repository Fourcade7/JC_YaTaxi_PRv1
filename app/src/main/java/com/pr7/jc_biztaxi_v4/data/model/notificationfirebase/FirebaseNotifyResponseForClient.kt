package com.pr7.jc_biztaxi_v4.data.model.notificationfirebase

data class FirebaseNotifyResponseForClient(
    val direction_id: Int,
    val from_direction: String,
    val status: String,
    val to_direction: String
)