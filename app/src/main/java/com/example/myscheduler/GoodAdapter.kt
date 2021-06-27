package com.example.myscheduler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavHost
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.*
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration
import org.bson.types.ObjectId

/*
* Scheduleコレクションデータ表示用クラス(Realm取得データをRecyclerViewに表示するためのクラスRealmRecyclerViewAdapterを継承)
* EnrollAdapter: extends the Realm-provided RealmRecyclerViewAdapter to provide data for a RecyclerView to display
* Realm objects on screen to a user.
*/
internal class GoodAdapter(
    data: OrderedRealmCollection<Goods>,
    private val context: Context?,
    private val GS: MutableList<GS>,
):
    RealmRecyclerViewAdapter<Goods,GoodAdapter.ViewHolder>(data,true) {
    //private var user: User? = taskApp.currentUser()
    //private val partitionValue: String = "via_android_studio"
    //private val config = SyncConfiguration.Builder(user!!, partitionValue)
    //    .allowWritesOnUiThread(true)
    //    .allowQueriesOnUiThread(true)
    //    .schemaVersion(1)
    //    .build()

    // private lateinit var realm: Realm
    //private val realm: Realm = Realm.getInstance(config)

    //var rm: RequestManager? = context?.let { Glide.with(it) }
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
        //val goods = realm.where<Goods>().findAll().sort("_id")
        val goods: Goods? = getItem(position4)
        if(goods!=null) {
            holder.data = goods
            holder.shop_nm.text = GS[position4].shop_name
            holder.goods_nm.text = goods.goods_name
            //var imageResource = context.resources.getIdentifier(
            //    GS[position4].imageName,
            //     "drawable", context.packageName)
            val UURL = goods.goodURL
            //if (context != null) {
            //    Glide.with(view)
            //Glide.with(android.su)
            //Android用画像読み込みライブラリの使用
            if (context != null) {
                Glide.with(context)
                    .load(UURL)
                    .centerCrop()
                    .into(holder.good_image)
            }
            holder.itemView.setOnClickListener {
                listener?.invoke(goods._id)
            }
            holder.auth_udt_btn.setOnClickListener {
                listener?.invoke(goods._id)
            }
        }else {
            // make sure Glide doesn't load anything into this view until told otherwise
            if (context != null) {
                Glide.with(context).clear(holder.good_image)
            }
            // remove the placeholder (optional); read comments below
            holder.good_image.setImageDrawable(null)
        }

    }
    private var listener: ((ObjectId?) -> Unit)? = null
    private var listener2: ((NavHost?) -> Unit)? = null

    fun setOnItemClickListener(listener: (ObjectId?) -> Unit) {
        this.listener = listener
    }
    fun setOnClickListener(listener2: (NavHost?) -> Unit) {
        this.listener2 = listener2
    }


    internal inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var goods_nm: TextView = view.findViewById(R.id.goods_name_text_view)
        val shop_nm: TextView = view.findViewById(R.id.shop_name_text_view)
        val good_image: ImageView = view.findViewById(R.id.image)
        var data : Goods? = null
        val auth_udt_btn: FloatingActionButton = view.findViewById(R.id.auth_update_btn)
    }
    //override fun getItemCount(position3: Int): Int =
//}
    //   override fun getItemId(position3: Int): Long {
    //     Realm.getInstance(config).use { realm ->
    //          return realm.where<Schedule>().equalTo("_id", getItem(position3)?._id).count()
    //return sum(realm.where<Schedule>.findAll())
    //
    //   }

}



