package com.example.myscheduler

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myscheduler.databinding.FragmentShopsBinding

//const val ROW_POSITION = "ROW_POSITION"

class ShopsFragment : Fragment() {
    private var _binding:FragmentShopsBinding? = null
    private val binding get() = _binding!!

    //private var position: Int = 0

    //override fun onCreate(savedInstanceState: Bundle?) {
    //    super.onCreate(savedInstanceState)
    //    arguments?.let {
     //       position = it.getInt(ROW_POSITION)
    //    }
    //}

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    }