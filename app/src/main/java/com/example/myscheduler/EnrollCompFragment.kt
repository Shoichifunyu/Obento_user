package com.example.myscheduler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myscheduler.databinding.FragmentEnrollCompBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_layout.*
import kotlinx.android.synthetic.main.card_layout.view.*

//const val ROW_POSITION = "ROW_POSITION"

class EnrollCompFragment : Fragment(){
    private var _binding: FragmentEnrollCompBinding? = null
    private val binding get() = _binding!!

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realm = Realm.getDefaultInstance()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnrollCompBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            findNavController().navigate(R.id.action_to_enrollscrn)
        }

        binding.enrollComplist.layoutManager = LinearLayoutManager(context)
        val schedules = realm.where<Schedule>().findAll()
        val adapter = EnrollAdapter(schedules)
        binding.enrollComplist.adapter = adapter
        adapter.setOnItemClickListener { id ->
        id?.let {
        val action = EnrollCompFragmentDirections.actionToEnrollscrn(it)
        findNavController().navigate(action)
                }
           }
        //(activity as? MainActivity)?.setShopbuttonVisible(View.VISIBLE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

      override fun onDestroy() {
           super.onDestroy()
           realm.close()
      }

}