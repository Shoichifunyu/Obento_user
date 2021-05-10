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
import com.example.myscheduler.databinding.FragmentResGoodsBinding
import com.example.myscheduler.databinding.FragmentShopsBinding
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_layout.*
import kotlinx.android.synthetic.main.card_layout.view.*
import kotlinx.android.synthetic.main.fragment_enroll_comp.*
import kotlinx.android.synthetic.main.goods_card_layout.*
import java.lang.reflect.Array.newInstance

const val ROW_POSITION3 = "ROW_POSITION"

class resGoodsFragment : Fragment(){
    private var _binding: FragmentResGoodsBinding? = null
    private val binding get() = _binding!!

    private var position3: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position3 = it.getInt(ROW_POSITION3)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResGoodsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = resGoodAdapter(getresGoods(resources)).apply {
                setOnItemClickListener { position3 ->
                    // if (position2 == 0) {
                    position3?.let {
                    //    val action = resGoodsFragmentDirections.actionToEnroll(it)
                    //    findNavController().navigate(action)
                    }

                }
                //       }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}