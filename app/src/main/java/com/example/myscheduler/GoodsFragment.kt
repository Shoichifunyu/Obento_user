package com.example.myscheduler

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myscheduler.databinding.FragmentGoodsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm
import io.realm.kotlin.where
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_layout.*
import kotlinx.android.synthetic.main.card_layout.view.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_goods.*
import kotlinx.android.synthetic.main.goods_card_layout.*


class GoodsFragment : Fragment() {

    private var user: User? = taskApp.currentUser()
    private val partitionValue: String = "via_android_studio"
    //Realmとの同期設定
    private val config = SyncConfiguration.Builder(user!!, partitionValue)
        .allowWritesOnUiThread(true)
        .allowQueriesOnUiThread(true)
        .schemaVersion(1)
        .build()
    // private lateinit var realm: Realm
    private val realm: Realm = Realm.getInstance(config)
    //private lateinit var viewModel: NewItemViewModel
    //companion object{
    //    fun newInstance(position: Int):ShopsFragment{
    //        var shopsFragment = ShopsFragment()
    //        var bundle = Bundle()
    //        bundle.putInt("shop_key",position)
    //        shopsFragment.arguments = bundle
    //        return ShopsFragment()
    //    }
    // }

    private var _binding: FragmentGoodsBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGoodsBinding.inflate(inflater, container, false)
        return binding.root
    }

//class ShopsFragment : Fragment(){
//    private lateinit var shops: List<Shop>
//    override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view: View = inflater.inflate(R.layout.card_layout, container, false)

//        val cardview: CardView = view.findViewById(R.id.shop_card_view)
    //val adapter = ShopAdapter(shopList,ShopAdapter.OnClickListener{shop ->
    //    view.findNavController().navigate(
    //           ShopsFragmentDirections.actionShopsFragmentToGoodsFragment(shop))
//        cardview.setOnClickListener { view.findNavController().navigate(R.id.action_shopsFragment_to_goodsFragment) }

    //       return view
    //   }

    //    cardview.adapter = adapter
    //    return view }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val viewModel: UserModel = ViewModelProvider(this).get(UserModel::class.java)
        //viewModel = ViewModelProvider(this).get(NewItemViewModel::class.java)
       // val textView: TextView = view.findViewById(R.id.text_newitem)
        //viewModel.text.observe(viewLifecycleOwner, Observer<String> {
            // Update the UI
        //    textView.text = it
       // })


        //Realm DBとの同期
        Realm.getInstanceAsync(config, object : Realm.Callback() {
            //成功時
            override fun onSuccess(realm: Realm) {
                // since this realm should live exactly as long as this activity, assign the realm to a member variable
                //同期したRealmインスタンスを親クラスEnrollCompFragmentのインスタンスに設定
                val realm = this@GoodsFragment.realm

                binding.goods.layoutManager = LinearLayoutManager(context)
                val goods = realm.where<Goods>().findAll()
                //adapterにGoodAdapterを適用し、goodsを代入し、getGoods関数によって処理する。
                val adapter = GoodAdapter(goods, context, getGoods(resources))
                binding.goods.adapter = adapter
                adapter.setOnItemClickListener { id ->
                    //{ val udt_btn = view.findViewById<FloatingActionButton>(R.id.auth_update_btn)
                    //udt_btn.setOnClickListener
                    id.let {
                        val action = GoodsFragmentDirections.actionToEnroll(it)
                            findNavController().navigate(action)
                    }
                }
                //adapter.setOnClickListener {
                //       val action2 = GoodsFragmentDirections.actionToEnrollAuth()
                //        findNavController().navigate(action2)
               //     }
            }
        })
    }

            override fun onDestroyView() {
                super.onDestroyView()
                _binding = null
            }
        }



