package com.ngopidulz.app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AdminProductActivity : AppCompatActivity() {

    private lateinit var adapter: AdminProductAdapter
    private val productList = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_product)

        val rvProduct = findViewById<RecyclerView>(R.id.rvProduct)
        val btnAdd = findViewById<Button>(R.id.btnAddProduct)

        adapter = AdminProductAdapter(productList,
            onEdit = { editProductDialog(it) },
            onDelete = { deleteProduct(it) }
        )

        rvProduct.layoutManager = LinearLayoutManager(this)
        rvProduct.adapter = adapter

        btnAdd.setOnClickListener {
            addProductDialog()
        }
    }

    private fun addProductDialog() {
        val view = layoutInflater.inflate(R.layout.dialog_add_product, null)
        val name = view.findViewById<EditText>(R.id.edtName)
        val desc = view.findViewById<EditText>(R.id.edtDesc)
        val price = view.findViewById<EditText>(R.id.edtPrice)

        AlertDialog.Builder(this)
            .setTitle("Tambah Produk")
            .setView(view)
            .setPositiveButton("Tambah") { _, _ ->
                val newProduct = Product(
                    name = name.text.toString(),
                    desc = desc.text.toString(),
                    price = price.text.toString().toInt(),
                    imageRes = R.drawable.keranjang, // default
                    id = System.currentTimeMillis().toInt(),
                    quantity = 0
                )
                productList.add(newProduct)
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun editProductDialog(product: Product) {
        val view = layoutInflater.inflate(R.layout.dialog_add_product, null)
        val name = view.findViewById<EditText>(R.id.edtName)
        val desc = view.findViewById<EditText>(R.id.edtDesc)
        val price = view.findViewById<EditText>(R.id.edtPrice)

        name.setText(product.name)
        desc.setText(product.desc)
        price.setText(product.price.toString())

        AlertDialog.Builder(this)
            .setTitle("Edit Produk")
            .setView(view)
            .setPositiveButton("Update") { _, _ ->
                product.name = name.text.toString()
                product.desc = desc.text.toString()
                product.price = price.text.toString().toInt()
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun deleteProduct(product: Product) {
        AlertDialog.Builder(this)
            .setTitle("Hapus Produk?")
            .setMessage("Yakin ingin menghapus produk ini?")
            .setPositiveButton("Hapus") { _, _ ->
                productList.remove(product)
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}
