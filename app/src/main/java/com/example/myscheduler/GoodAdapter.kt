package com.example.myscheduler

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
internal class GoodAdapter(data: OrderedRealmCollection<Goods>,
                            private val context: Context?,
                            private val GS: MutableList<GS>):
    RealmRecyclerViewAdapter<Goods,GoodAdapter.ViewHolder>(data,true) {
    private var user: User? = taskApp.currentUser()
    private val partitionValue: String = "via_android_studio"

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GoodAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.goods_card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GoodAdapter.ViewHolder, position4: Int) {
        val goods: Goods? = getItem(position4)
        holder.data = goods
        holder.shop_nm.text = GS[position4].shop_name
        holder.goods_nm.text = goods?.goods_name
        //var imageResource = context.resources.getIdentifier(
        //    GS[position4].imageName,
       //     "drawable", context.packageName)
        val UURL = goods?.goodURL
        if (context != null) {
            Glide.with(context)
                .load(UURL)
                .centerCrop()
                .into(holder.good_image)
        }
        holder.itemView.setOnClickListener {
            listener?.invoke(goods?._id)
        }
    }

    private var listener: ((ObjectId?) -> Unit)? = null

    fun setOnItemClickListener(listener: (ObjectId?) -> Unit) {
        this.listener = listener

    }


    internal inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var goods_nm: TextView = view.findViewById(R.id.goods_name_text_view)
        val shop_nm: TextView = view.findViewById(R.id.shop_name_text_view)
        val good_image: ImageView = view.findViewById(R.id.image)
        var data: Goods? = null
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



