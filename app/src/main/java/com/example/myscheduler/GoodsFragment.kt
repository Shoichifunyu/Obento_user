package com.example.myscheduler

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myscheduler.databinding.FragmentGoodsBinding
import com.example.myscheduler.databinding.FragmentShopsBinding
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_layout.*
import kotlinx.android.synthetic.main.card_layout.view.*
import kotlinx.android.synthetic.main.fragment_enroll_comp.*
import kotlinx.android.synthetic.main.goods_card_layout.*
import java.lang.reflect.Array.newInstance

//const val ROW_POSITION = "ROW_POSITION"

class GoodsFragment : Fragment(){
    private var _binding: FragmentGoodsBinding? = null
    private val binding get() = _binding!!

//    private lateinit var realm: Realm

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
 //       realm = Realm.getDefaultInstance()
//    }

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
        //val shops = getShops(resources)
        //binding.shopsKind.text = shops[position].shop_name
        //shop_button.setOnClickListener{
        //    navController.navigate(R.id.action_nav_goods)
        //}
        //view.findViewById<Button>(R.id.button_second).setOnClickListener {
        //     findNavController().navigate(R.id.action_to_enroll)
       // }



        binding.root.apply {
            layoutManager = LinearLayoutManager(context)
            //adapter = GoodAdapter(getGoods(resources))

            adapter = GoodAdapter(getGoods(resources)).apply {
        //binding.goods.layoutManager = LinearLayoutManager(context)
       // val schedules = realm.where<Schedule>().findAll()
      //  val adapter3 = EnrollAdapter(schedules)
       // binding.goods.adapter = adapter3
                setOnItemClickListener { position2 ->
                      if (position2 == 0) {
                        position2?.let {
                           findNavController().navigate(R.id.action_to_enroll)
                    //parentFragmentManager?.let{manager: FragmentManager ->
                 //   val tag = "EnrollCompFragment"
                 //   var fragment = manager.findFragmentByTag(tag)
                 //   if (fragment == null) {
                //        fragment = EnrollCompFragment()
                 //       manager.beginTransaction().apply{
                 //           replace(R.id.goods,fragment,tag)
                 //       }.commit()
                    }

                        }
                      //       }
        }
                       }
                   }
    }

    //fun onShopButtonPressed(view: View){
    //val action = ShopsFragmentDirections.actionShopsFragmentToGoodsFragment()
    //    findNavController().navigate(R.id.action_shopsFragment_to_goodsFragment)
    //}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

  //  override fun onDestroy() {
 //       super.onDestroy()
 //       realm.close()
 //   }

}