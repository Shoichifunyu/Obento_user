package com.example.myscheduler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.BundleCompat
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myscheduler.databinding.FragmentGoodsBinding
import com.example.myscheduler.databinding.FragmentShopsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm
import io.realm.kotlin.where
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_layout.*
import kotlinx.android.synthetic.main.card_layout.view.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.reflect.Array.newInstance

internal class GoodsFragment : Fragment() {

    private var user: User? = taskApp.currentUser()
    private val partitionValue: String = "via_android_studio"
    private val config = SyncConfiguration.Builder(user!!, partitionValue)
        .allowWritesOnUiThread(true)
        .allowQueriesOnUiThread(true)
        .schemaVersion(1)
        .build()
    // private lateinit var realm: Realm
    private val realm: Realm = Realm.getInstance(config)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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

        Realm.getInstanceAsync(config, object : Realm.Callback() {
            override fun onSuccess(realm: Realm) {
                // since this realm should live exactly as long as this activity, assign the realm to a member variable
                //同期したRealmインスタンスを親クラスEnrollCompFragmentのインスタンスに設定
                val realm = this@GoodsFragment.realm

                binding.goods.layoutManager = LinearLayoutManager(context)
                val goods = realm.where<Goods>().findAll()
                val adapter = GoodAdapter(goods,context, getGoods(resources))
                binding.goods.adapter = adapter
                adapter.setOnItemClickListener { id ->
                    // if (position2 == 0) {
                    id?.let {
                        findNavController().navigate(R.id.action_to_enroll)
                        //parentFragmentManager?.let{manager: FragmentManager ->
                        //   val tag = "EnrollCompFragment"
                        //   var fragment = manager.findFragmentByTag(tag)
                        //   if (fragment == null) {
                        //        fragment = EnrollCompFragment()
                        //       manager.beginTransaction().apply{
                        //           replace(R.id.goods,fragment,tag)
                        //       }.commit()
                        //            }

                        //                  }
                    }
                }
            }
        })
    }

            override fun onDestroyView() {
                super.onDestroyView()
                _binding = null
            }
        }

