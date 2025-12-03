package com.ngopidulz.app

data class Product(
    var name: String,
    var desc: String,
    var price: Int,
    var imageRes: Int,
    var id: Int,
    var quantity: Int = 0
)
