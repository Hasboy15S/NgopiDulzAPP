package com.ngopidulz.app

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object OrderStorage {

    private const val PREF = "orders_pref"
    private const val KEY = "orders_list"

    fun saveOrder(context: Context, order: Order) {
        val sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val gson = Gson()

        val list = getOrders(context).toMutableList()
        list.add(order)

        sp.edit().putString(KEY, gson.toJson(list)).apply()
    }

    fun getOrders(context: Context): List<Order> {
        val sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val gson = Gson()

        val json = sp.getString(KEY, null) ?: return emptyList()

        val type = object : TypeToken<List<Order>>() {}.type
        return gson.fromJson(json, type)
    }

    fun updateOrder(context: Context, updatedOrder: Order) {
        val list = getOrders(context).toMutableList()
        val index = list.indexOfFirst { it.id == updatedOrder.id }

        if (index >= 0) {
            list[index] = updatedOrder
            val sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            sp.edit().putString(KEY, Gson().toJson(list)).apply()
        }
    }
    fun deleteOrder(context: Context, orderId: Int) {
        val list = getOrders(context).toMutableList()
        val newList = list.filter { it.id != orderId }   // hapus berdasarkan ID

        val sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        sp.edit().putString(KEY, Gson().toJson(newList)).apply()
    }


}
