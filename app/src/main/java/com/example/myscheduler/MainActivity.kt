package com.example.myscheduler

import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_DATA
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myscheduler.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm
import io.realm.mongodb.User
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_layout.*
import kotlinx.android.synthetic.main.card_layout.view.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_enroll_comp.*
import kotlinx.android.synthetic.main.fragment_goods.*
import kotlinx.android.synthetic.main.goods_card_layout.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var user: User? = null
    private lateinit var realm: Realm
    private lateinit var recyclerView: RecyclerView
    val EXTRA_MESSAGED: String = "com.example.myscheduler.MESSAGE"


    //Realm.init(this)
    //val realmConfig = RealmConfiguration.Builder() // Realmの設定を定義
     //   .schemaVersion(1L) // 新しいスキーマのバージョンを設定
     //   .migration(MyMigration()) // マイグレーション用のコードを設定
     //   .build()
    //Realm.setDefaultConfiguration(realmConfig)
        override fun onStart() {
        super.onStart()
        //ログイン中ユーザの取得
        try {
            user = taskApp.currentUser()
            println(user)
        } catch (e: IllegalStateException) {
            Log.w(TAG(), e)
        }
        //ログイン中ユーザが存在しない時、ログイン画面を表示する
        if (user == null) {
            // if no user is currently logged in, start the login activity so the user can authenticate
            startActivity(Intent(this, LoginActivity::class.java))
        }
        //ログイン中ユーザが存在するとき
        else {
            //Realm設定に、ログイン中ユーザおよびpartitionを適用
            //(partitionValueは、使用するpartitionに合わせて変更)
            // configure realm to use the current user and the partition corresponding to "My Project"
            //val config = SyncConfiguration.Builder(user!!, "via_android_studio")
                   //.waitForInitialRemoteData()
            //        .allowWritesOnUiThread(true)
            //        .allowQueriesOnUiThread(true)
            //        .schemaVersion(1)
            //        .build()

            //上記設定をデフォルトとして保存
            // save this configuration as the default for this entire app so other activities and threads can open their own realm instances
            //Realm.setDefaultConfiguration(config)
            //val intentD: Intent = Intent(this@MainActivity, LoginActivity::class.java)
            //val str : String = "Check"
            //intentD.putExtra(EXTRA_MESSAGED, str)
            //startActivity(intentD)
            //val intent: Intent = getIntent()
            //var data1 = intent.getStringExtra(LoginActivity().EXTRA_MESSAGE)
            //data1 = "Customer"
            //println("chk"+data1)
            //if (data1 == "Authorizer"){
            //    auth_enroll_fab.visibility = View.VISIBLE
            //} else {
            //    auth_enroll_fab.visibility = View.GONE
           // }
          //  val uiThreadRealm = Realm.getInstance(config)
          //  addChangeListenerToRealm(uiThreadRealm)

            //バックグラウンド処理でRealm DBと同期し、成功したらRecyclerViewを呼び出す
            // Sync all realm changes via a new instance, and when that instance has been successfully created connect it to an on-screen list (a recycler view)
            //Realm.getInstanceAsync(config, object : Realm.Callback() {
            //    override fun onSuccess(realm: Realm) {
                    // since this realm should live exactly as long as this activity, assign the realm to a member variable
                    //同期したRealmインスタンスを親クラスMainActivityのインスタンスに設定
              //      this@MainActivity.realm = realm
                    //ShopsFragmentを呼び出す
                    //setUpRecyclerView(realm)
                    //setupActionBarWithNavController(naviController)
             //   }
           // })
            val naviController = findNavController(R.id.nav_host_fragment)
            naviController.navigateUp()
        }
    }

//    fun addChangeListenerToRealm(realm : Realm) {
        // all tasks in the realm
//        val tasks : RealmResults<Schedule> = realm.where<Schedule>().findAllAsync()
//        tasks.addChangeListener(OrderedRealmCollectionChangeListener<RealmResults<Schedule>> { collection, changeSet ->
            // process deletions in reverse order if maintaining parallel data structures so indices don't change as you iterate
 //           val deletions = changeSet.deletionRanges
//            for (i in deletions.indices.reversed()) {
//                val range = deletions[i]
 //               Log.v("QUICKSTART", "Deleted range: ${range.startIndex} to ${range.startIndex + range.length - 1}")
//            }
//            val insertions = changeSet.insertionRanges
//            for (range in insertions) {
 //               Log.v("QUICKSTART", "Inserted range: ${range.startIndex} to ${range.startIndex + range.length - 1}")
//            }
 //           val modifications = changeSet.changeRanges
//            for (range in modifications) {
 //               Log.v("QUICKSTART", "Updated range: ${range.startIndex} to ${range.startIndex + range.length - 1}")
 //           }
//        })
//    }

    //override fun onStop() {
     //   super.onStop()
       // user.run {
        //    realm.close()
       // }
   // }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent: Intent = getIntent()
        var data1 = intent.getStringExtra(LoginActivity().EXTRA_MESSAGE)
        //data1 = "Customer"
        println("chk"+data1)
        if (data1 == "Authorizer"){
            println("Authorizer")
            auth_enroll_fab.visibility = View.VISIBLE
        } else {
            println("Customer")
            auth_enroll_fab.visibility = View.GONE
        }
        auth_enroll_fab.setOnClickListener {
            val action = ShopsFragmentDirections.actionToEnrollAuth()
            findNavController(R.id.nav_host_fragment).navigate(action)
            auth_enroll_fab.visibility = View.GONE
        }
        //radioB1 = findViewById(R.id.radioButton)
        //radioB2 = findViewById(R.id.radioButton2)

        //Realm.init(this)
        //val realmConfig = RealmConfiguration.Builder() // Realmの設定を定義
                //.allowWritesOnUiThread(true)
        //        .schemaVersion(1L) // 新しいスキーマのバージョンを設定
        //   .migration(MyMigration()) // マイグレーション用のコードを設定
        //   .build()
        //Realm.setDefaultConfiguration(realmConfig)

        //Realm.setDefaultConfiguration(realmConfig)

        //MainActivityクラス内のプロパティ初期化(lateinit変数のためonCreate実行時に初期化される)
        // default instance uses the configuration created in the login activity
       // val Config = RealmConfiguration.Builder()
        //    .name("Task.realm")
       //     .schemaVersion(1)
       //     .build()
       // realm = Realm.getInstance(Config)//Realmのインスタンス
       // recyclerView = findViewById(R.id.task_list)//RecyclerViewのインスタンス
        //fab = findViewById(R.id.floating_action_button)//タスク作成ボタンのインスタンス

        //タスク作成ボタンを押したとき、新しいタスク名を入力するダイアログを作成
        // create a dialog to enter a task name when the floating action button is clicked
        //fab.setOnClickListener {
        //    val input = EditText(this)
        //    val dialogBuilder = AlertDialog.Builder(this)
        //    dialogBuilder.setMessage("Enter task name:")
        //        .setCancelable(true)
        //       .setPositiveButton("Create") { dialog, _ -> run {
        //            dialog.dismiss()
        //            val task = Task(input.text.toString())
                     //all realm writes need to occur inside of a transaction
        //            realm.executeTransactionAsync { realm ->
        //                realm.insert(task)
        //            }
        //        }
        //        }
        //        .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel()
        //        }

        //    val dialog = dialogBuilder.create()
       //     dialog.setView(input)
        //    dialog.setTitle("Create New Task")
        //    dialog.show()
        }

    //override fun onDestroy() {
     //   super.onDestroy()
        //recyclerView.adapter = null
        // if a user hasn't logged out when the activity exits, still need to explicitly close the realm
      //  realm.close()
   // }

    //logoutメニューをMainActivity上に設置
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.activity_task_menu, menu)
        return true
    }

    //logoutメニューを押したときの処理(ログアウト)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                user?.logOutAsync {
                    if (it.isSuccess) {
                        // always close the realm when finished interacting to free up resources
                //        realm.close()
                        user = null
                        Log.v(TAG(), "user logged out")
                        startActivity(Intent(this, LoginActivity::class.java))
                    } else {
                        Log.e(TAG(), "log out failed! Error: ${it.error}")
                    }
                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
//RecyclerViewを初期設定して呼び出し、ID順で表示
//private fun setUpRecyclerView(realm: Realm) {
    // a recyclerview requires an adapter, which feeds it items to display.
    // Realm provides RealmRecyclerViewAdapter, which you can extend to customize for your application
    // pass the adapter a collection of Tasks from the realm
    // sort this collection so that the displayed order of Tasks remains stable across updates
//    adapter = TaskAdapter(realm.where<Task>().sort("_id").findAll())
//    recyclerView.layoutManager = LinearLayoutManager(this)
//    recyclerView.adapter = adapter
//    recyclerView.setHasFixedSize(true)
//    recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
//}
   // companion object {
   //     lateinit var auth_enroll_fab: FloatingActionButton
  //  }
}