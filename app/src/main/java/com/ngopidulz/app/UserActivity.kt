package com.ngopidulz.app


import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.ImageButton
import kotlin.jvm.java


class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        setupMenuItem(R.id.menu_jadwal, "Jadwal Shift")
        setupMenuItem(R.id.menu_detail, "Detail Akun")
        setupMenuItem(R.id.menu_transaksi, "Riwayat Transaksi")
        setupMenuItem(R.id.menu_pesanan, "Riwayat Pemesanan")

        // --- Bagian 2: Lainnya ---
        setupMenuItem(R.id.menu_bantuan, "Pusat Bantuan")
        setupMenuItem(R.id.menu_tentang, "Tentang Kami")
        setupMenuItem(R.id.menu_lokasi, "Lokasi Outlet")
        setupMenuItem(R.id.menu_notif, "Pengaturan Notifikasi")

        val btnJadwal = findViewById<View>(R.id.menu_jadwal)
        btnJadwal.setOnClickListener{
            val intent = Intent(this, JadwalActivity::class.java)
            startActivity(intent)
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

    // Fungsi ajaib untuk mengubah teks menu
    private fun setupMenuItem(viewId: Int, text: String) {
        val includedView = findViewById<View>(viewId)
        val textView = includedView.findViewById<TextView>(R.id.menu_text)
        textView.text = text

        includedView.setOnClickListener{
            android.widget.Toast.makeText(this, "Kamu menekan menu: $text", android.widget.Toast.LENGTH_SHORT).show()
        }

        // Nanti kalau mau menu bisa diklik, tambahkan kode di sini
    }
}
