package com.ngopidulz.app

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ProductStorage {

    private const val PREF = "product_pref"
    private const val KEY = "product_list"
    fun clear(context: Context) {
        val sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        sp.edit().clear().apply()
    }

    fun saveProducts(context: Context, list: List<Product>) {
        val sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(list)

        sp.edit().putString(KEY, json).apply()
    }

    fun getProducts(context: Context): List<Product> {
        val sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sp.getString(KEY, null) ?: return emptyList()

        val type = object : TypeToken<List<Product>>() {}.type
        return gson.fromJson(json, type)
    }

    fun isEmpty(context: Context): Boolean {
        val sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return sp.getString(KEY, null) == null
    }
}
