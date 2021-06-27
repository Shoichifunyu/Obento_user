package com.example.myscheduler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShopAdapter (
    private val shops: List<Shop>
):RecyclerView.Adapter<ShopAdapter.ViewHolder>(){
    private var listener: ((Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (Int) -> Unit){
        this.listener = listener
    }
    class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        val shop_name: TextView = cell.findViewById(R.id.shop_name_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.shop_name.text = shops[position].shop_name
        holder.itemView.setOnClickListener{
            listener?.invoke(position)
        }
    }

    override fun getItemCount(): Int = shops.size
}