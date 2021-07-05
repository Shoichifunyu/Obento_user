package com.example.myscheduler

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.myscheduler.BuildConfig.MONGODB_REALM_APP_ID
import com.example.myscheduler.databinding.ActivityMainBinding
import io.realm.Realm
import io.realm.internal.objectstore.OsSyncUser
import io.realm.kotlin.syncSession
import io.realm.kotlin.where
import io.realm.mongodb.App
import io.realm.mongodb.User
import io.realm.mongodb.auth.EmailPasswordAuth
import io.realm.mongodb.sync.SyncConfiguration
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_layout.*
import kotlinx.android.synthetic.main.card_layout.view.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_enroll_comp.*
import kotlinx.android.synthetic.main.fragment_goods.*
import kotlinx.android.synthetic.main.goods_card_layout.*
import java.net.URL


public class MainActivity : AppCompatActivity(){
    var data1: String? = "chack"
    private lateinit var binding: ActivityMainBinding
    private var user: User? = null
    private lateinit var realm: Realm
    private lateinit var recyclerView: RecyclerView
    var app = App(MONGODB_REALM_APP_ID)
    //private lateinit var username: String//ユーザ名(Eメールアドレス)入力用テキストボックス
    //private lateinit var password: String//パスワード入力用テキストボックス


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
            //println("line?")
            //val creds = Credentials.emailPassword(username, password)
            //println(creds)
            //taskApp.loginAsync(creds) {
            //    println(username)
           //     println(password)
           // }

            //var iii = c
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
            //navigationに遷移する
            val naviController = findNavController(R.id.nav_host_fragment)
            naviController.navigateUp()
            //startActivity(intent)
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
        //data1 = intent.getStringExtra(LoginActivity().EXTRA_MESSAGE)
        //data1 = "Customer"
        //val intent2: Intent = Intent(this@MainActivity, ShopsFragment::class.java)
        //println("chk"+data1)
        //if (data1 == "Authorizer"){
        //    println("Authorizer")
        //    auth_enroll_fab.visibility = View.VISIBLE
       // } else {
       //     println("Customer")
            //auth_enroll_fab.visibility = View.GONE
       // }
       // intent2.putExtra(EXTRA_MESSAGE, data1)
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