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
            "Americano",
            "Espresso yang dicampur air panas menghasilkan rasa kopi yang ringan dan clean.",
            15000,
            R.drawable.amer,
            id = 1
        ),
        Product(
            "Latte",
            "Perpaduan espresso dan susu steamed creamy dengan sedikit foam lembut.",
            18000,
            R.drawable.latte,
            id = 2
        ),
        Product(
            "Kopi Gula Aren",
            "Kopi susu creamy dengan campuran gula aren premium yang manis dan wangi.",
            20000,
            R.drawable.guren,
            id = 3
        ),
        Product(
            "Cheese Cake",
            "Cheese cake lembut dengan tekstur creamy dan rasa keju yang nikmat.",
            25000,
            R.drawable.cake,
            id = 4
        ),
        Product(
            "Kentang Goreng",
            "Kentang goreng renyah di luar lembut di dalam, cocok jadi teman ngopi.",
            15000,
            R.drawable.cake,
            id = 5
        ),
        Product(
            "Croissant",
            "Croissant lembut berlapis, wangi butter, cocok disantap saat hangat.",
            17000,
            R.drawable.crois,
            id = 6
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        if (ProductStorage.isEmpty(this)) {
            ProductStorage.saveProducts(this, productList)
        }
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

        val adapter = ProductAdapter(productList) {}



        recyclerView.adapter = adapter
    }
}
