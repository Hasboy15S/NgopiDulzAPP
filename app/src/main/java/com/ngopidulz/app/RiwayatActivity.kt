package com.ngopidulz.app

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RiwayatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_riwayat)
        val recycler = findViewById<RecyclerView>(R.id.recyclerRiwayat)
        recycler.layoutManager = LinearLayoutManager(this)

        val list = OrderStorage.getOrders(this)

        recycler.adapter = RiwayatAdapter(list) { updated ->
            OrderStorage.updateOrder(this, updated)
        }
        val btnHome = findViewById<ImageButton>(R.id.btnhome);
        val btnShop = findViewById<ImageButton>(R.id.btnshop);
        val btnKeranjang = findViewById<ImageButton>(R.id.btnkeranjang);
        val btnRiwayat = findViewById<ImageButton>(R.id.btnriwayat);
        val btnUser = findViewById<ImageButton>(R.id.btnuser);
        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnShop.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }
        btnKeranjang.setOnClickListener {
            val intent = Intent(this, KeranjangActivity::class.java)
            startActivity(intent)
        }
        btnRiwayat.setOnClickListener {
            val intent = Intent(this, RiwayatActivity::class.java)
            startActivity(intent)
        }
        btnUser.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }
    }
}