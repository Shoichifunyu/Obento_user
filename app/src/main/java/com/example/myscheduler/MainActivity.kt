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
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myscheduler.databinding.ActivityMainBinding
import com.example.myscheduler.databinding.FragmentShopsBinding
import com.google.android.material.appbar.AppBarLayout
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_layout.*
import kotlinx.android.synthetic.main.card_layout.view.*
import kotlinx.android.synthetic.main.fragment_enroll_comp.*
import kotlinx.android.synthetic.main.fragment_goods.*
import kotlinx.android.synthetic.main.goods_card_layout.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private lateinit var realm: Realm

    //private lateinit var shbinding: FragmentShopsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //realm = Realm.getDefaultInstance()

       // val adapter1 = ShopAdapter(getShops(resources))
        val adapter2 = GoodAdapter(getGoods(resources))
        //val adapter3 = EnrollAdapter(realm.where<Schedule>().findAll())
        //val adapter = ConcatAdapter(ShopAdapter(getShops(resources)),GoodAdapter(getGoods(resources)))

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        //val layoutManager = LinearLayoutManager(this)

        //  val recyclerView: RecyclerView = findViewById(R.id.shopsFragment)
        //it.onButtonClickに、このクラスに作成した関数を参照として渡す
        //  recyclerView.adapter = ShopAdapter(shops).also { shop_button.setOnClickListener { }

        // recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

       // adapter1.setOnItemClickListener { position ->
       //     if (position == 0) {
       //         position?.let {
                    //   DataBindingUtil.setContentView(,R.layout.card_layout)
                    //   val goodsFragment = GoodsFragment()
                    //val appBarConfiguration = AppBarConfiguration(navController.graph)
                    //val appBarConfiguration = AppBarConfiguration(setOf(R.id.shopsFragment, R.id.goodsFragment))
          //          val navController = findNavController(R.id.nav_host_fragment)

          //          setupActionBarWithNavController(navController)
                    //shop_button.setOnClickListener { view ->
                    //       replaceFragment(goodsFragment)}
                    //    val listItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_shops, AppBarLayout, false)
                    //    listItemBinding.shop_button.setOnClickListener {

          //          navController.navigate(R.id.action_nav_goods)
                    //}
                    //}
                    // }
          //      }
                //if (position == 1) {
                //   position?.let {
                //   DataBindingUtil.setContentView(,R.layout.card_layout)
                //   val goodsFragment = GoodsFragment()
                //       val navController = findNavController(R.id.nav_host_fragment)
                //val appBarConfiguration = AppBarConfiguration(navController.graph)
                //val appBarConfiguration = AppBarConfiguration(setOf(R.id.shopsFragment, R.id.goodsFragment))
                //       setupActionBarWithNavController(navController)
                //shop_button.setOnClickListener { view ->
                //       replaceFragment(goodsFragment)}
                //    val listItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_shops, AppBarLayout, false)
                //    listItemBinding.shop_button.setOnClickListener {

                //       navController.navigate(R.id.action_nav_goods2)

                //     }
                //   }
         //   }
         //   adapter2.setOnItemClickListener { position2 ->
         //       if (position2 == 0) {
         //           position2?.let {
         //               val navController = findNavController(R.id.nav_host_fragment)
         //               setupActionBarWithNavController(navController)
         //               navController.navigate(R.id.action_to_enroll)
         //           }


            //val adapter2 = GoodAdapter(getGoods(resources)).apply{
            //fab.setOnClickListener {
           //     val tag = "EnrollCompFragment"
           //     var fragment = supportFragmentManager.findFragmentByTag(tag)
            //    if (fragment == null){
           //         fragment = EnrollCompFragment()
           //     supportFragmentManager.beginTransaction().apply{
            //            replace(R.id.goods,fragment,tag)
            //        }.commit()
            //           }
          //      }
        //    }


           // val adapter3 = EnrollAdapter(schedules)
            //adapter3.setOnItemClickListener { id ->
            //    id?.let {
            //        val navController = findNavController(R.id.nav_host_fragment)
             //       val action = EnrollCompFragmentDirections.actionToEnrollscrn(it)
             //       navController.navigate(action)
                    //                            }
                    //        if (position == 1) {
                    //            position?.let {
                    //                val action2 = ShopsFragmentDirections.actionNavGoods2()
                    //                findNavController().navigate(action2)
                    //           }
            //    }
     //       }
            //(activity as? MainActivity)?.setShopbuttonVisible(View.VISIBLE)
        }
        }


   // override fun onSupportNavigateUp()
   //    = findNavController(R.id.nav_host_fragment).navigateUp()

    //override fun onDestroy() {
    //    super.onDestroy()
      //  realm.close()
    //}

    //fun setShopbuttonVisible(visibility: Int){
    //    shop_button.visibility = visibility
    //}
//fun replaceFragment(fragment: Fragment) {
    //    val fragmentManager = supportFragmentManager
    //    val fragmentTransaction = fragmentManager.beginTransaction()
    //    fragmentTransaction.replace(R.id.container, fragment)
     //   fragmentTransaction.commit()
   // }
//}