package com.ngopidulz.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdminAdapter(
    private val orders: MutableList<Order>,
    private val onDelete: (Order) -> Unit
) : RecyclerView.Adapter<AdminAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val txtOrderId: TextView = v.findViewById(R.id.txtOrderId)
        val txtOrderItems: TextView = v.findViewById(R.id.txtOrderItems)
        val txtOrderTotal: TextView = v.findViewById(R.id.txtOrderTotal)
        val btnDelete: Button = v.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_admin_riwayat, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = orders.size

    override fun onBindViewHolder(h: ViewHolder, position: Int) {
        val order = orders[position]

        h.txtOrderId.text = "Order #${order.id}"

        h.txtOrderItems.text = order.items.joinToString("\n") {
            "${it.name} ${it.qty}x"
        }

        h.txtOrderTotal.text = "Total: Rp ${order.total}"

        h.btnDelete.setOnClickListener {
            onDelete(order)
        }
    }
}
