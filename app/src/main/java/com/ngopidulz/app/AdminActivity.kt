package com.ngopidulz.app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AdminActivity : AppCompatActivity() {

    private lateinit var adapter: AdminAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val recycler = findViewById<RecyclerView>(R.id.recyclerAdmin)

        val list = OrderStorage.getOrders(this).toMutableList()

        adapter = AdminAdapter(list) { order ->
            OrderStorage.deleteOrder(this, order.id)
            list.remove(order)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Order dihapus", Toast.LENGTH_SHORT).show()
        }

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnLogout.setOnClickListener {
            val sp = getSharedPreferences("login_pref", MODE_PRIVATE)
            sp.edit().clear().apply()   // ⬅️ hapus semua data login

            Toast.makeText(this, "Berhasil logout", Toast.LENGTH_SHORT).show()

            // restart kembali ke LoginActivity
            val intent = Intent(this, LoginPage::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
        val btnproduk = findViewById<Button>(R.id.btnProduks)
        btnproduk.setOnClickListener{
            val intent = Intent(this, AdminProductActivity::class.java)
            startActivity(intent)
        }
    }
}
