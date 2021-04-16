package com.example.myscheduler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class GoodAdapter (
    private val goods: List<Goods>
):RecyclerView.Adapter<GoodAdapter.ViewHolder>(){
    private var listener: ((Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (Int) -> Unit){
        this.listener = listener
    }
    class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        val goods_name: TextView = cell.findViewById(R.id.goods_name_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.goods_card_layout, parent, false)
        //val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GoodAdapter.ViewHolder, position: Int) {
        holder.goods_name.text = goods[position].goods_name
        holder.itemView.setOnClickListener{
            listener?.invoke(position)
        }
    }

    override fun getItemCount(): Int = goods.size
}
