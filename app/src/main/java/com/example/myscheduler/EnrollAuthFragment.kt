package com.example.myscheduler

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myscheduler.databinding.FragmentEnrollAuthBinding
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration
import java.io.File


internal class EnrollAuthFragment : Fragment() {
    private var _binding: FragmentEnrollAuthBinding? = null
    private val binding get() = _binding!!
    private var user: User? = taskApp.currentUser()
    //private var gUri: Uri? = null
    private lateinit var goodView: ImageView
    private val partitionValue: String = "via_android_studio"
    private val config = SyncConfiguration.Builder(user!!, partitionValue)
        .allowWritesOnUiThread(true)
        .allowQueriesOnUiThread(true)
        .schemaVersion(1)
        .build()

    private lateinit var documentDir: File
    private lateinit var shareFile: File

    // private lateinit var realm: Realm
    private val realm: Realm = Realm.getInstance(config)
    //private val args: EnrollAuthFragmentArgs by navArgs()

    //lateinit var getUri : ActivityResultLauncher<String>

    private val startForResult = registerForActivityResult(ActivityResultContracts.GetContent(), this::saveEnroll)
   // { uri ->  Log.d("EnrollAuthFragment", "uri: $uri")
   // }
    //private val receiveForResult = registerForActivityResult(ActivityResultContracts.) { uri ->
    //    Log.d("EnrollAuthFragment", "geturi: $uri")
    //}
    //    Realm.getInstanceAsync(config, object : Realm.Callback() {
    //        override fun onSuccess(realm: Realm) {
     //           if (args.goodId != null) {
    //                val goods = realm.where<Goods>()
      //                  .equalTo("_id", args.goodId).findFirst()
     //               binding.goodName.setText(goods?.goods_name)
                    //if (URL != null) {
                    //    goods?.goodURL = URL
                    //}
     //           }
                //private fun saveEnroll(view: View, URL: String?) {
      //          when (args.goodId) {
     //               null -> {
     //                   realm.executeTransaction { transactionRealm ->
     //                       val maxId = realm.where<Goods>().count()
     //                       //val maxId = realm.where<Schedule>().max()
      //                      val nextId = (maxId.toLong() ?: 0L) + 1L
       //                     val goods = realm.createObject<Goods>(nextId)
      //                      goods.goods_name = binding.goodName.text.toString()
      //                      //setFragmentResultListener("URL") { key, bundle ->
                            //    val UR = bundle.getString("URL")
                            //    println("URL:" + URL)
     //                       println("URL:" + uri)
      //                      goods.goodURL = uri.toString()
     //                       println(goods.goodURL)
     //                   }
     //               }
     //               else -> {
     //                   realm.executeTransaction { db: Realm ->
     //                       val goods =
     //                           db.where<Goods>().equalTo("_id", args.goodId).findFirst()
     //                       goods?.goods_name = binding.goodName.text.toString()
      //                      //setFragmentResultListener("URL") { key, bundle ->
      //                      //     val URL = bundle.getString("URL")
      //                      //    println("URL:" + URL)
      //                      println("URL:" + uri)
       //                     goods?.goodURL = uri.toString()
      //                  }
      //              }
     //           }
    //        }
   //     })
  //  }
    //val intent = Intent()
    //private val REQUEST_PERMISSION : Int = 10
    //private val READ_REQUEST_CODE = 42

    //private var uri: Uri? = null

    //val startForResult = registerForActivityResult(
        // ActivityResultContracts.StartActivityForResult())
    //    ActivityResultContracts.StartActivityForResult()
    //)
    //val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
   // { result: ActivityResult? ->
        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.
        //if (uri?.resultCode == Activity.RESULT_OK) {

        //The document selected by the user won't be returned in the intent.
        //Instead, a URI to that document will be contained in the return intent
        // provided to this method as a parameter.
        // Pull that URI using resultData.getData().
      //  if (result != null) {
      //      if (result.data != null) {
       //         var pfDescriptor: ParcelFileDescriptor? = null
      //          try {
      //              var uri: String = result.data.toString()

     //               println("koko?:"+uri)
                    //              // Uriを表示
     //               textView?.setText(
     //                   java.lang.String.format(
     //                       Locale.US,
      //                      "Uri:　%s",
     //                       uri.toString()
     //                   )
     //               )
                    //pfDescriptor = uri?.let {
                   //     context?.getContentResolver()
                   //         ?.openFileDescriptor(it, "r")
                    //}
                   // if (pfDescriptor != null) {
                   //    val fileDescriptor: FileDescriptor =
                   //              pfDescriptor.fileDescriptor
                   //  val bmp = BitmapFactory.decodeFileDescriptor(fileDescriptor)
                  //    pfDescriptor.close()
                  //    goodView?.setImageBitmap(bmp)
                  //   }
       //             } catch (e: IOException) {
        //                e.printStackTrace()
        //            } finally {
        //               try {
         //                 pfDescriptor?.close()
         //       } catch (e: Exception) {
         //           e.printStackTrace()
        //              }
        //        }
       //     }
     //   }
    //}


    //override fun onCreate(savedInstanceState: Bundle?) {
   //     super.onCreate(savedInstanceState)
       // getUri = ActivityResultRegistry.register("key", owner,
        //    ActivityResultContracts.GetContent()
       // ) { uri ->

   // }

    //textView = findViewById(R.id.text_View)
    //imageView = findViewById(R.id.goodImage)

    //schema versionの整合、権限設定
    //val config = RealmConfiguration.Builder()
    //.name("Schedule.realm")
    //          .name("via_android_studio")
    //         .deleteRealmIfMigrationNeeded()
    //         .allowWritesOnUiThread(true)
    //        .allowQueriesOnUiThread(true)
    //      .schemaVersion(1)
    //        .build()
    // realm = Realm.getInstance(config)

    //}

    //try {
    //     user = taskApp.currentUser()
    //} catch (e: IllegalStateException) {
    //    Log.w(TAG(), e)
    //}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnrollAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val args: EnrollAuthFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Intent(Intent.ACTION_OPEN_DOCUMENT)
        //intent.addCategory(Intent.CATEGORY_OPENABLE)
        //intent.type = "image/*"
        //intent.action = Intent.ACTION_GET_CONTENT
        //startForResult.launch(intent)
        // textView = view.findViewById(R.id.text_View
        //documentDir = File(requireContext().filesDir.toString() + "/document")
        //shareFile = File(documentDir.toString() + "/share_file")
        // var uri = FileProvider.getUriForFile(
        //     requireContext(),
        //     BuildConfig.APPLICATION_ID + ".fileprovider",
        //      shareFile
        //  )
        //val projection = arrayOf(MediaStore.MediaColumns.DISPLAY_NAME)
        // val cursor: Cursor? = requireContext().contentResolver.query(uri!!, projection, null, null, null)
        // if (cursor != null) {
        //     var uri: String? = null
        //    if (cursor.moveToFirst()) {
        //         uri = cursor.getString(0)
        //     }
        //      cursor.close()
        //  }
        //println(uri)


        //goodView = view.findViewById(R.id.goodImage)
        //val getContent = registerForActivityResult(ActivityResultContracts.GetContent(), this::onContent)
        //val startForResult =
        //    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
        //        if (result?.resultCode == Activity.RESULT_OK) {
        //            result.data?.let { data: Intent ->
        //                val uri: Uri? = result.data!!.getData()
        //textView!!.text =
        //    java.lang.String.format(Locale.US, "Uri:　%s", uri.toString())
        //                 println("uri:" + uri)
        //                 return@let uri
        //goodView.setImageURI(uri ?: return@let)
        //Toast.makeText(context, "$uri", Toast.LENGTH_LONG).show()
        //             }
        //         }
        //     }

        //val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        //    if (it) {
        //        saveEnroll(view, return@registerForActivityResult)
        //     }
        // }
        //println("startForResult:"+startForResult)
        //startForResult.apply {
        //    println("startForResult:" + result.data)
        // }
        //   goodView.setImageURI(uri).toString()
        //
        //val URL = textView?.setText(
        //    String.format(Locale.US, "Uri:　%s",intent.data.toString()));
        //println("URL:"+URL)

        //binding.upload.setOnClickListener {
            //val intent: Intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            //    type = "image/*"
            //    addCategory(Intent.CATEGORY_OPENABLE)
           // }
            //println("nani:" + startForResult.launch("image/*"))
            //startForResult.launch("image/*")
            //var uri = receiveForResult.launch(WRITE_EXTERNAL_STORAGE)
            // Handle the returned Uri
            //println("nani:" + onContent())

            //registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->


                //val photoUri: Uri? = intent.data
                //var cursor: Cursor? = null
                //var filePath: String? = ""



                Realm.getInstanceAsync(config, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm) {
                        if (args.goodId != null) {
                            println("not null")
                            val goods = realm.where<Goods>()
                                .equalTo("_id", args.goodId).findFirst()
                            binding.goodName.setText(goods?.goods_name)
                            binding.goodTxtURL.text = goods?.goodURL
                            context?.let {
                                Glide.with(it)
                                    .load(goods?.goodURL)
                                    .centerCrop()
                                    .into(binding.goodImage)
                            }
                        }

                            binding.authSave.setOnClickListener {
                                //val dialog = ConfirmDialog("画像をアップロード",
                                //"OK",
                                //setFragmentResultListener("requestKey") { key, bundle ->
                                //    val uri = bundle.getString("resultKey")
                                //    println("geturi:" + uri)
                                    val dialog = ConfirmDialog("保存しますか", "保存する（画像を選択）",
                                        {startForResult.launch("image/*")
                                            //setFragmentResult(
                                            //    "URL",
                                            //    bundleOf("URL" to startForResult.launch(intent)))
                                            // setFragmentResultListener("URL") { key, bundle ->
                                            //     val URL = bundle.getString("URL")
                                            //launcher.launch(WRITE_EXTERNAL_STORAGE)

                                        },
                                        "キャンセル",
                                       {
                                       //     Snackbar.make(it, "キャンセルしました。", Snackbar.LENGTH_SHORT)
                                       //         .show()
                                        }
                                    )
                                    // setFragmentResultListener("URL") { key, bundle ->
                                    //    val URL = bundle.getString("URL")
                                    //     println("URL:$URL")
                                    dialog.show(parentFragmentManager, "save_dialog")
                               // }
                         //   }
                        }
                    }
            })
         }


        private fun saveEnroll(URL: Uri?) {
            when (args.goodId) {
                null -> {
                    realm.executeTransaction { transactionRealm ->
                        val maxId = realm.where<Goods>().count()
                        //val maxId = realm.where<Schedule>().max()
                        val nextId = (maxId.toLong() ?: 0L) + 1L
                        val goods = realm.createObject<Goods>(nextId)
                        goods.goods_name = binding.goodName.text.toString()
                        //setFragmentResultListener("URL") { key, bundle ->
                        //    val UR = bundle.getString("URL")
                        //    println("URL:" + URL)
                       println("URL:" + URL)
                        goods.goodURL = URL.toString()
                        println(goods.goodURL)
                    }
                    //Snackbar.make(view, "追加しました", Snackbar.LENGTH_SHORT)
                    //    .setAction("戻る") { findNavController().popBackStack() }
                    //    .setActionTextColor(Color.YELLOW)
                    //   .show()
                }
                else -> {
                    realm.executeTransaction { db: Realm ->
                       val goods =
                            db.where<Goods>().equalTo("_id", args.goodId).findFirst()
                        goods?.goods_name = binding.goodName.text.toString()
                        //setFragmentResultListener("URL") { key, bundle ->
                        //     val URL = bundle.getString("URL")
                        //    println("URL:" + URL)
                      println("URL:" + URL)
                        goods?.goodURL = URL.toString()
                    }
                   // Snackbar.make(view, "修正しました", Snackbar.LENGTH_SHORT)
                  //      .setAction("戻る") { findNavController().popBackStack() }
                  //      .setActionTextColor(Color.YELLOW)
                  //      .show()
                }
            }
       }


        //private fun onContent(uri: Uri?): Uri? {
        //    println(uri)
        //    return uri
       // }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

        override fun onDestroy() {
            super.onDestroy()
            realm.close()
        }
    }

