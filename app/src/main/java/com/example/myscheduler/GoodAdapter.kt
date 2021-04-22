package com.example.myscheduler

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

class GoodAdapter (
        private val GS: MutableList<GS>
):RecyclerView.Adapter<GoodAdapter.ViewHolder>() {
    private var listener: ((Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        val shop_nm: TextView = cell.findViewById(R.id.shop_name_text_view)
        val goods_nm: TextView = cell.findViewById(R.id.goods_name_text_view)
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.goods_card_layout, parent, false)
            //val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: GoodAdapter.ViewHolder, position2: Int) {
            holder.shop_nm.text = GS[position2].shop_name
            holder.goods_nm.text = GS[position2].goods_name
            holder.itemView.setOnClickListener {
                listener?.invoke(position2)
            }
        }

        override fun getItemCount(): Int = GS.size
    }