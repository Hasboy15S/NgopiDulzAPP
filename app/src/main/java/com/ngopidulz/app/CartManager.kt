package com.ngopidulz.app

object CartManager {
    // Simpan produk yang ada di keranjang (mutable supaya adapter bisa memakai referensi)
    private val cartItems = mutableListOf<Product>()

    // Tambah produk (jika sudah ada -> increment quantity)
    fun addToCart(product: Product) {
        val existing = cartItems.find { it.id == product.id }
        if (existing != null) {
            existing.quantity++
        } else {
            cartItems.add(product.copy(quantity = 1))
        }
    }

    // Akses list keranjang (return MutableList supaya adapter bisa modify jika perlu)
    fun getCart(): MutableList<Product> = cartItems

    // Increase quantity pada produk yang ada di cart (mencari berdasarkan id)
    fun increase(product: Product) {
        val existing = cartItems.find { it.id == product.id } ?: return
        existing.quantity++
    }

    // Decrease quantity (hapus jika quantity jadi 0)
    fun decrease(product: Product) {
        val existing = cartItems.find { it.id == product.id } ?: return
        if (existing.quantity > 1) {
            existing.quantity--
        } else {
            cartItems.remove(existing)
        }
    }

    // Total harga semua produk (harga * qty)
    fun getItems(): List<Product> {
        return cartItems
    }

    fun getTotal(): Int {
        return cartItems.sumOf { it.price * it.quantity }
    }

    fun clear() {
        cartItems.clear()
    }

}
