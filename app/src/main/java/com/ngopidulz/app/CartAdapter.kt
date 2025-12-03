package com.ngopidulz.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val items: MutableList<Product>,
    private val onChange: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val img = v.findViewById<ImageView>(R.id.imgCart)
        val name = v.findViewById<TextView>(R.id.txtCartName)
        val price = v.findViewById<TextView>(R.id.txtCartPrice)
        val btnMin = v.findViewById<ImageView>(R.id.btnMin)
        val btnPlus = v.findViewById<ImageView  >(R.id.btnPlus)
        val qty = v.findViewById<TextView>(R.id.txtQty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(v)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(h: CartViewHolder, pos: Int) {
        val p = items[pos]
        h.img.setImageResource(p.imageRes)
        h.name.text = p.name
        h.price.text = "Rp ${p.price * p.quantity}"
        h.qty.text = p.quantity.toString()

        h.btnPlus.setOnClickListener {
            CartManager.increase(p)
            notifyItemChanged(pos)
            onChange()
        }

        h.btnMin.setOnClickListener {
            CartManager.decrease(p)
            // jika item dihapus, refresh list reference
            if (!CartManager.getCart().contains(p)) {
                items.removeAt(pos)
                notifyItemRemoved(pos)
                notifyItemRangeChanged(pos, items.size)
            } else {
                notifyItemChanged(pos)
            }
            onChange()
        }
    }
}
