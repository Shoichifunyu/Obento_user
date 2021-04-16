package com.example.myscheduler

import android.content.Context
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.view.MenuItem
import android.graphics.drawable.Drawable.Callback
import android.util.AttributeSet
import android.view.accessibility.AccessibilityEventSource
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myscheduler.databinding.ActivityMainBinding
import com.example.myscheduler.databinding.FragmentShopsBinding
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_layout.*
import kotlinx.android.synthetic.main.card_layout.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private lateinit var shbinding: FragmentShopsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ShopAdapter(getShops(resources))
        //val layoutManager = LinearLayoutManager(this)

        //  val recyclerView: RecyclerView = findViewById(R.id.shopsFragment)
        //it.onButtonClickに、このクラスに作成した関数を参照として渡す
        //  recyclerView.adapter = ShopAdapter(shops).also { shop_button.setOnClickListener { }

        // recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //val tag = "ShopsFragment"
        //var fragment = supportFragmentManager.findFragmentByTag(tag)
        //if (fragment == null) {
        //    fragment = ShopsFragment()
        //    supportFragmentManager.beginTransaction().apply {
        //        replace(R.id.content, fragment, tag)
        //    }.commit()
        //}
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)



        adapter.setOnItemClickListener { position ->
            if (position == 0) {
                position?.let {
                    //   DataBindingUtil.setContentView(,R.layout.card_layout)
                    //   val goodsFragment = GoodsFragment()
                    val navController = findNavController(R.id.nav_host_fragment)
                    //val appBarConfiguration = AppBarConfiguration(navController.graph)
                    //val appBarConfiguration = AppBarConfiguration(setOf(R.id.shopsFragment, R.id.goodsFragment))
                    setupActionBarWithNavController(navController)
                    //shop_button.setOnClickListener { view ->
                    //       replaceFragment(goodsFragment)}
                    //    val listItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_shops, AppBarLayout, false)
                    //    listItemBinding.shop_button.setOnClickListener {

                    navController.navigate(R.id.action_nav_goods)
                    //}
                    //}
                }
            }
                if (position == 1){
            position?.let {
                //   DataBindingUtil.setContentView(,R.layout.card_layout)
                //   val goodsFragment = GoodsFragment()
                val navController = findNavController(R.id.nav_host_fragment)
                //val appBarConfiguration = AppBarConfiguration(navController.graph)
                //val appBarConfiguration = AppBarConfiguration(setOf(R.id.shopsFragment, R.id.goodsFragment))
                setupActionBarWithNavController(navController)
                //shop_button.setOnClickListener { view ->
                //       replaceFragment(goodsFragment)}
                //    val listItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_shops, AppBarLayout, false)
                //    listItemBinding.shop_button.setOnClickListener {

                navController.navigate(R.id.action_nav_goods2)

                }
            }
        }
    }

    override fun onSupportNavigateUp()
       = findNavController(R.id.nav_host_fragment).navigateUp()

    //fun setShopbuttonVisible(visibility: Int){
    //    shop_button.visibility = visibility
    //}

//fun replaceFragment(fragment: Fragment) {
    //    val fragmentManager = supportFragmentManager
    //    val fragmentTransaction = fragmentManager.beginTransaction()
    //    fragmentTransaction.replace(R.id.container, fragment)
     //   fragmentTransaction.commit()
   // }
}