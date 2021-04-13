package com.example.myscheduler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShopAdapter (
        private val context: Context,
        private val shops: List<Shop>
        ):RecyclerView.Adapter<ShopAdapter.ViewHolder>(){

            class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
                val shop_name: TextView = view.findViewById(R.id.name)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.shop_name.text = shops[position].shop_name
    }

    override fun getItemCount(): Int = shops.size

        }
