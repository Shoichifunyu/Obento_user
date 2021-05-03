package com.example.myscheduler

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class EnrollAdapter(data: OrderedRealmCollection<Schedule>):
RealmRecyclerViewAdapter<Schedule,EnrollAdapter.ViewHolder>(data, true){

    private var listener: ((Long?) -> Unit)? = null

    fun setOnItemClickListener(listener:(Long?) -> Unit) {
        this.listener = listener
    }
    init{
        setHasStableIds(true)}

class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell){
    val date: TextView = cell.findViewById(android.R.id.text1)
    val personname: TextView = cell.findViewById(android.R.id.text2)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnrollAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.simple_list_item_2,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: EnrollAdapter.ViewHolder, position3: Int) {
        val schedule: Schedule? = getItem(position3)
        holder.date.text = DateFormat.format("yyyy/MM/dd HH:mm", schedule?.date)
        holder.personname.text = schedule?.personname
        holder.itemView.setOnClickListener {
            listener?.invoke(schedule?.scheduleId)
        }
    }

    override fun getItemId(position3: Int): Long {
        return getItem(position3)?.scheduleId ?: 0
    }
}
