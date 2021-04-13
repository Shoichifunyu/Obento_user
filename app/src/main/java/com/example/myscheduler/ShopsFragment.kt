package com.example.myscheduler

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myscheduler.databinding.FragmentShopsBinding
import kotlinx.android.synthetic.main.card_layout.*

//const val ROW_POSITION = "ROW_POSITION"

class ShopsFragment : Fragment(R.layout.fragment_shops) {
    private var _binding:FragmentShopsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentShopsBinding.inflate(inflater, container, false)
        return binding.root
            }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val shops = getShops(resources)
        //binding.shopsKind.text = shops[position].name
        binding.root.apply{
            layoutManager =LinearLayoutManager(context)
            adapter = ShopAdapter(context, getShops(resources))
        }

        shop_button.setOnClickListener{
            val action = ShopsFragmentDirections.actionShopsFragmentToGoodsFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    }