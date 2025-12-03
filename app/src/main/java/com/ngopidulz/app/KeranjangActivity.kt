package com.ngopidulz.app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class KeranjangActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CartAdapter
    private lateinit var txtTotal: TextView
    private lateinit var btnCheckout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keranjang)

        recyclerView = findViewById(R.id.recyclerKeranjang)
        txtTotal = findViewById(R.id.txtTotal)
        btnCheckout = findViewById(R.id.btnCheckout)

        val cart = CartManager.getCart()

        adapter = CartAdapter(cart) { updateTotal() }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        updateTotal()

        btnCheckout.setOnClickListener {

            val orderItems = CartManager.getItems().map {
                OrderItem(
                    name = it.name,
                    price = it.price,
                    qty = it.quantity       // ← gunakan quantity dari CartManager
                )
            }

            val totalHarga = CartManager.getTotal()

            val newOrder = Order(
                id = System.currentTimeMillis().toInt(),
                items = orderItems,
                total = totalHarga,
                status = "Proses",
                timestamp = System.currentTimeMillis()
            )

            // SIMPAN KE RIWAYAT
            OrderStorage.saveOrder(this, newOrder)

            CartManager.clear()

            Toast.makeText(this, "Berhasil Checkout!", Toast.LENGTH_SHORT).show()

            finish()
        }

    }

    private fun updateTotal() {
        val total = CartManager.getTotal()
        txtTotal.text = "Total: Rp $total"
    }

}

