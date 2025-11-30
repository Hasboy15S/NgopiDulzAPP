package com.ngopidulz.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ShopAdapter(
    private val items: List<Product>
) : RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    // ViewHolder untuk ShopAdapter (sesuaikan id view dengan item_product.xml)
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imgProduct)
        val name: TextView = view.findViewById(R.id.txtName)
        val desc: TextView? = view.findViewById(R.id.txtDesc) // optional: ada jika layout punya
        val price: TextView = view.findViewById(R.id.txtPrice)
        val btnAdd: Button = view.findViewById(R.id.btnTambah) // sesuai id di XML
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // pastikan nama layout sesuai: item_product atau item_produk
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.name.text = item.name
        holder.desc?.text = item.desc
        holder.price.text = "Rp ${item.price}"
        holder.image.setImageResource(item.imageRes)

        holder.btnAdd.setOnClickListener {
            CartManager.addToCart(item)
            Toast.makeText(holder.itemView.context, "${item.name} ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
            // jika ingin memperbarui tampilan (mis. ubah tombol menjadi qty), lakukan notifyItemChanged(position)
            notifyItemChanged(position)
        }
    }
}
