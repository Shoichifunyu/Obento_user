package com.example.myscheduler

import android.content.Intent
import android.content.res.Configuration
import android.icu.text.Transliterator
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
import com.example.myscheduler.databinding.FragmentShopsBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_layout.*
import kotlinx.android.synthetic.main.card_layout.view.*
import java.lang.reflect.Array.newInstance

const val ROW_POSITION = "ROW_POSITION"

class ShopsFragment : Fragment() {
    //companion object{
    //    fun newInstance(position: Int):ShopsFragment{
    //        var shopsFragment = ShopsFragment()
    //        var bundle = Bundle()
    //        bundle.putInt("shop_key",position)
    //        shopsFragment.arguments = bundle
    //        return ShopsFragment()
    //    }
    // }

    private var _binding: FragmentShopsBinding? = null
    private val binding get() = _binding!!

    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ROW_POSITION)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopsBinding.inflate(inflater, container, false)
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
        //view.findViewById<Button>(R.id.shop_button).setOnClickListener {
        //shop_button.setOnClickListener {
        //     findNavController().navigate(R.id.action_nav_goods)
        //}
        val bundle = Bundle()

        binding.root.apply {
            layoutManager = LinearLayoutManager(context)
            //adapter = ShopAdapter(context, getShops(resources))

            adapter = ShopAdapter(getShops(resources)).apply {
                //setOnItemClickListener { position: Int ->
                //navController.navigate(R.id.action_nav_goods)
                //parentFragmentManager?.let {
                //    manager: FragmentManager ->
                //    val tag = "GoodsFragment"
                //    var fragment = manager.findFragmentByTag(tag)
                //    if (fragment == null) {
                //        fragment = GoodsFragment()
                //        fragment.arguments = Bundle().apply{
                //            putInt(ROW_POSITION, position)
                //        }
                //        manager.beginTransaction().apply {
                //            replace(R.id.content, fragment, tag)
                //            addToBackStack(null)
                //        }.commit()
                //    }
                setOnItemClickListener { position ->
                    if (position == 0) {
                        position?.let {
                            val action = ShopsFragmentDirections.actionNavGoods()
                            findNavController().navigate(action)
                        }
                        if (position == 2) {
                            val action = ShopsFragmentDirections.actionNavResgoods()
                            findNavController().navigate(action)

                        }

                    }
            //if (position == 1) {
                    //   position?.let {
                    //      val action2 = ShopsFragmentDirections.actionNavGoods2()
                    //     findNavController().navigate(action2)
                    //   }
                    //  }
                    //    }
                    //(activity as? MainActivity)?.setShopbuttonVisible(View.VISIBLE)
                    // }
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
}

