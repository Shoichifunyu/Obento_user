package com.example.myscheduler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myscheduler.databinding.FragmentShopsBinding

const val ROW_POSITION = "ROW_POSITION"

class ShopsFragment : Fragment() {

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
        //決まり文句
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
        //val strValue01 = requireArguments().getString("VALUE01")

        binding.root.apply {
            layoutManager = LinearLayoutManager(context)
            //ShopAdapterにGetShopsを代入して適用
            adapter = ShopAdapter(getShops(resources)).apply {
                setOnItemClickListener { position ->
                    if (position == 0) {
                        position.let {
                            val action = ShopsFragmentDirections.actionNavGoods()
                            findNavController().navigate(action)
                        }
                    }
                    if (position == 2) {
                        val action = ShopsFragmentDirections.actionNavResgoods()
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}