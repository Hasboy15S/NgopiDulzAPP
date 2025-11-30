package com.ngopidulz.app

data class Product(
    val name: String,
    val desc: String,
    val price: Int,
    val imageRes: Int,
    val id: Int,
    var quantity: Int = 0
)
