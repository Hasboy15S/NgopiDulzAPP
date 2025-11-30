package com.ngopidulz.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ngopidulz.app.Product
import com.ngopidulz.app.R

class ProductAdapter(
    private val items: List<Product>,
    private val onAddToCart: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProduct: ImageView = view.findViewById(R.id.imgProduct)
        val txtName: TextView = view.findViewById(R.id.txtName)
        val txtDesc: TextView = view.findViewById(R.id.txtDesc)
        val txtPrice: TextView = view.findViewById(R.id.txtPrice)
        val btnTambah: Button = view.findViewById(R.id.btnTambah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.imgProduct.setImageResource(item.imageRes)
        holder.txtName.text = item.name
        holder.txtDesc.text = item.desc
        holder.txtPrice.text = "Rp ${item.price}"

        holder.btnTambah.setOnClickListener {
            onAddToCart(item)
        }
    }

    override fun getItemCount(): Int = items.size
}
