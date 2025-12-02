package com.ngopidulz.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ngopidulz.app.ProductAdapter


class ShopActivity : AppCompatActivity() {

    private val productList = listOf(
        Product(
            "Kopi Hitam",
            "Kopi hitam pahit dengan aroma kuat khas arabika",
            12000,
            R.drawable.keranjang,
            id = 1
        ),
        Product(
            "Cappuccino",
            "Campuran espresso, susu, dan foam lembut",
            18000,
            R.drawable.keranjang,
            id = 2
        ),
        Product(
            "Es Kopi Susu",
            "Kopi susu dengan rasa creamy dan manis seimbang",
            20000,
            R.drawable.keranjang,
            id = 3
        ),
        Product(
            "Americano",
            "Espresso yang dicampur air menghasilkan rasa ringan",
            15000,
            R.drawable.keranjang,
            id = 4
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        // === BOTTOM MENU ===
        findViewById<ImageButton>(R.id.btnhome).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        findViewById<ImageButton>(R.id.btnshop).setOnClickListener {
            startActivity(Intent(this, ShopActivity::class.java))
        }
        findViewById<ImageButton>(R.id.btnkeranjang).setOnClickListener {
            startActivity(Intent(this, KeranjangActivity::class.java))
        }
        findViewById<ImageButton>(R.id.btnriwayat).setOnClickListener {
            startActivity(Intent(this, RiwayatActivity::class.java))
        }
        findViewById<ImageButton>(R.id.btnuser).setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }

        // === SETUP RECYCLER VIEW ===
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerShop)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = ProductAdapter(productList) {
            // Kosong pun tidak apa-apa
        }

        recyclerView.adapter = adapter
    }
}
