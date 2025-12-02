package com.ngopidulz.app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_page)
        val inputName = findViewById<EditText>(R.id.inputName)
        val inputPassword = findViewById<EditText>(R.id.inputpw)
        val btnLogin = findViewById<Button>(R.id.btnlogin)

        // Ketika tombol login di klik
        btnLogin.setOnClickListener {
            val username = inputName.text.toString()
            val password = inputPassword.text.toString()

            // Validasi login
            if (username == "user" && password == "user") {
                // Berpindah ke MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Supaya tidak bisa kembali ke login
            }
            if (username == "admin" && password == "admin") {
                val i = Intent(this, AdminActivity::class.java)
                startActivity(i)
                finish()
                return@setOnClickListener
            }

            else {
                Toast.makeText(this, "Nama atau password salah!", Toast.LENGTH_SHORT).show()
            }
        }
        val register = findViewById<TextView>(R.id.registerLink)
        register.setOnClickListener {
            val intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
        }
    }
}