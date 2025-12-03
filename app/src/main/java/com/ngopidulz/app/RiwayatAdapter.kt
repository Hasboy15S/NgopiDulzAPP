package com.ngopidulz.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RiwayatAdapter(
    private val orders: List<Order>,
    private val onStatusChanged: (Order) -> Unit
) : RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val txtTitle = v.findViewById<TextView>(R.id.txtTitle)
        val txtStatus = v.findViewById<TextView>(R.id.txtStatus)
        val txtItems = v.findViewById<TextView>(R.id.txtItems)
        val txtTotal = v.findViewById<TextView>(R.id.txtTotal)
        val btnSelesai = v.findViewById<Button>(R.id.btnSelesai)
    }

    override fun onCreateViewHolder(p: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(p.context)
            .inflate(R.layout.item_riwayat, p, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = orders.size

    override fun onBindViewHolder(h: ViewHolder, pos: Int) {
        val o = orders[pos]

        // Judul
        h.txtTitle.text = "Penjualan ${pos + 1}"

        // Daftar item
        h.txtItems.text = o.items.joinToString("\n") { item ->
            "${item.name} ${item.qty}x"
        }

        // Total harga
        h.txtTotal.text = "RP ${o.total}"

        // Status
        h.txtStatus.text = o.status

        if (o.status == "Selesai") {
            h.btnSelesai.visibility = View.GONE
        } else {
            h.btnSelesai.visibility = View.VISIBLE
        }

        h.btnSelesai.setOnClickListener {
            o.status = "Selesai"
            onStatusChanged(o)
            notifyItemChanged(pos)
        }
    }
}
