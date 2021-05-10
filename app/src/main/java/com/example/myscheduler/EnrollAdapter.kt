package com.example.myscheduler

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import io.realm.*
import io.realm.Realm.init
import io.realm.kotlin.where
import io.realm.mongodb.ErrorCode.Type.JSON
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration
import okio.Utf8.size
import org.bson.types.ObjectId
import java.nio.file.Files.size

/*
* Scheduleコレクションデータ表示用クラス(Realm取得データをRecyclerViewに表示するためのクラスRealmRecyclerViewAdapterを継承)
* EnrollAdapter: extends the Realm-provided RealmRecyclerViewAdapter to provide data for a RecyclerView to display
* Realm objects on screen to a user.
*/
internal class EnrollAdapter(data: OrderedRealmCollection<Schedule>):
RealmRecyclerViewAdapter<Schedule,EnrollAdapter.ViewHolder>(data,true) {
    private var user: User? = taskApp.currentUser()
    private val partitionValue: String = "via_android_studio"
    private val config = SyncConfiguration.Builder(user!!, partitionValue)
        .allowWritesOnUiThread(true)
        .allowQueriesOnUiThread(true)
        .schemaVersion(1)
        .build()
     private lateinit var realm: Realm
    //private val realm: Realm = Realm.getInstance(config)

             init {
                 setHasStableIds(true)
             }

             override fun onCreateViewHolder(
                 parent: ViewGroup,
                 viewType: Int
             ): EnrollAdapter.ViewHolder {
                 val inflater = LayoutInflater.from(parent.context)
                 val view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false)
                 return ViewHolder(view)
             }

             override fun onBindViewHolder(holder: EnrollAdapter.ViewHolder, position3: Int) {
                 val schedule: Schedule? = getItem(position3)
                 holder.data = schedule
                 holder.date.text = DateFormat.format("yyyy/MM/dd HH:mm", schedule?.date)
                 holder.personname.text = schedule?.personname
                 holder.itemView.setOnClickListener {
                     listener?.invoke(schedule?._id)

                         //Realm.getInstance(config).use{ realm ->
                         //realm.where<Schedule>().equalTo("_id", getItem(position3)?._id).count()}

                 }
             }

             private var listener: ((ObjectId?) -> Unit)? = null

             fun setOnItemClickListener(listener: (ObjectId?) -> Unit) {
                 this.listener = listener
             }


             internal inner class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
                 var date: TextView = cell.findViewById(android.R.id.text1)
                 var personname: TextView = cell.findViewById(android.R.id.text2)
                 var data: Schedule? = null
             }
             //override fun getItemCount(position3: Int): Int =
//}


          //   override fun getItemId(position3: Int): Long {
            //     Realm.getInstance(config).use { realm ->
           //          return realm.where<Schedule>().equalTo("_id", getItem(position3)?._id).count()
                     //return sum(realm.where<Schedule>.findAll())
           //      }
          //   }

         }



