package com.ngopidulz.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdminProductAdapter(
    private val productList: ArrayList<Product>,
    private val onEdit: (Product) -> Unit,
    private val onDelete: (Product) -> Unit
) : RecyclerView.Adapter<AdminProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val txtName: TextView = item.findViewById(R.id.txtName)
        val txtPrice: TextView = item.findViewById(R.id.txtPrice)
        val txtDesc: TextView = item.findViewById(R.id.txtDesc)
        val btnEdit: Button = item.findViewById(R.id.btnEdit)
        val btnDelete: Button = item.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_admin_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val p = productList[position]

        holder.txtName.text = p.name
        holder.txtPrice.text = "Rp ${p.price}"
        holder.txtDesc.text = p.desc

        holder.btnEdit.setOnClickListener { onEdit(p) }
        holder.btnDelete.setOnClickListener { onDelete(p) }
    }

    override fun getItemCount(): Int = productList.size
}
