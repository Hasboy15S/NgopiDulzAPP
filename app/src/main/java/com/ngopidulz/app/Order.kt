package com.ngopidulz.app
data class Order(
    val id: Int,
    val items: List<OrderItem>,
    val total: Int,
    var status: String,
    val timestamp: Long
)
