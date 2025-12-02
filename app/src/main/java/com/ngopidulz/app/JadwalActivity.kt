package com.ngopidulz.app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class JadwalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal)

        val btnBack = findViewById<View>(R.id.btn_back)

        btnBack.setOnClickListener{
            finish()
        }
    }
}
