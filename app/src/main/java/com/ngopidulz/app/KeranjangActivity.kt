package com.ngopidulz.app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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

        // ambil referensi list dari CartManager (MutableList)
        val cart = CartManager.getCart()

        adapter = CartAdapter(cart, onChange = { updateTotal() })
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        updateTotal()

        btnCheckout.setOnClickListener {
            // aksi checkout singkat: clear cart, refresh adapter, update total
            CartManager.clear()
            adapter.notifyDataSetChanged()
            updateTotal()
        }
    }

    private fun updateTotal() {
        val total = CartManager.getTotal()
        txtTotal.text = "Total: Rp $total"
    }
}
