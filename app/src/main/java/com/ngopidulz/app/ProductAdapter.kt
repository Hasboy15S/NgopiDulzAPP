package com.ngopidulz.app

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val items: List<Product>,
    private val onChange: () -> Unit
) : RecyclerView.Adapter<ProductAdapter.PViewHolder>() {

    inner class PViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val img = v.findViewById<ImageView>(R.id.imgProduct)
        val name = v.findViewById<TextView>(R.id.txtName)
        val desc = v.findViewById<TextView>(R.id.txtDesc)
        val price = v.findViewById<TextView>(R.id.txtPrice)

        val btnTambah = v.findViewById<Button>(R.id.btnTambah)
        val qtyLayout = v.findViewById<LinearLayout>(R.id.layoutQty)

        val btnMinus = v.findViewById<ImageView>(R.id.btnMinus)
        val btnPlus = v.findViewById<ImageView>(R.id.btnPlus)
        val edtQty = v.findViewById<EditText>(R.id.edtQty)  // qty di tengah
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return PViewHolder(v)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(h: PViewHolder, pos: Int) {
        val p = items[pos]

        // Set isi
        h.img.setImageResource(p.imageRes)
        h.name.text = p.name
        h.desc.text = p.desc
        h.price.text = "Rp ${p.price}"

        // UI default
        if (p.quantity == 0) {
            h.btnTambah.visibility = View.VISIBLE
            h.qtyLayout.visibility = View.GONE
        } else {
            h.btnTambah.visibility = View.GONE
            h.qtyLayout.visibility = View.VISIBLE
            h.edtQty.setText(p.quantity.toString())
        }

        // Tombol pertama kali
        h.btnTambah.setOnClickListener {
            p.quantity = 1
            CartManager.addToCart(p)

            h.btnTambah.visibility = View.GONE
            h.qtyLayout.visibility = View.VISIBLE
            h.edtQty.setText("1")

            onChange()
        }

        // Tombol Minus
        h.btnMinus.setOnClickListener {
            if (p.quantity > 1) {
                p.quantity--
                h.edtQty.setText(p.quantity.toString())
                CartManager.decrease(p)
            } else {
                // kembali ke tombol tambah
                p.quantity = 0
                CartManager.decrease(p)

                h.qtyLayout.visibility = View.GONE
                h.btnTambah.visibility = View.VISIBLE
            }
            onChange()
        }

        // Tombol Plus
        h.btnPlus.setOnClickListener {
            p.quantity++
            h.edtQty.setText(p.quantity.toString())
            CartManager.increase(p)
            onChange()
        }
    }
}
