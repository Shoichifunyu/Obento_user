package com.example.myscheduler

import android.graphics.Color
import android.hardware.camera2.params.ColorSpaceTransform
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.myscheduler.databinding.FragmentEnrollBinding
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.lang.IllegalArgumentException
import android.text.format.DateFormat
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import io.realm.RealmConfiguration
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration
import kotlinx.android.synthetic.main.fragment_enroll.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class EnrollFragment : Fragment() {
    private var _binding: FragmentEnrollBinding? = null
    private val binding get() = _binding!!
    private var user: User? = taskApp.currentUser()
    private val partitionValue: String = "via_android_studio"
    private val config = SyncConfiguration.Builder(user!!, partitionValue)
    .allowWritesOnUiThread(true)
    .allowQueriesOnUiThread(true)
    .schemaVersion(1)
            .build()
   // private lateinit var realm: Realm
    private val realm: Realm = Realm.getInstance(config)



    //override fun onCreate(savedInstanceState: Bundle?) {
    //    super.onCreate(savedInstanceState)

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

   // }

        //try {
       //     user = taskApp.currentUser()
        //} catch (e: IllegalStateException) {
        //    Log.w(TAG(), e)
        //}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentEnrollBinding.inflate(inflater,container,false)
        return binding.root
    }
    private val args: EnrollFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Realm.getInstanceAsync(config, object : Realm.Callback() {
            override fun onSuccess(realm: Realm) {
                if (args.enrollId != null) {
                    val schedule = realm.where<Schedule>()
                            .equalTo("_id", args.enrollId).findFirst()
                    binding.dateEdit.setText(DateFormat.format("yyyy/MM/dd", schedule?.date))
                    binding.timeEdit.setText(DateFormat.format("HH:mm", schedule?.date))
                    binding.PersonNameEdit.setText(schedule?.personname)
                    binding.detailEdit.setText(schedule?.detail)
                    // val schedule2 = Schedule()
                    //  realm.executeTransactionAsync { realm ->
                    //      realm.insert(schedule2)
                }
                // }
                binding.save.setOnClickListener {
                    val dialog = ConfirmDialog("保存しますか？", "保存",
                            { saveEnroll(it) }, "キャンセル", { Snackbar.make(it, "キャンセルしました。", Snackbar.LENGTH_SHORT).show() })
                    dialog.show(parentFragmentManager, "save_dialog")
                }
                binding.delete.setOnClickListener {
                    val dialog = ConfirmDialog("削除しますか？", "削除",
                            { deleteEnroll(it) }, "キャンセル", { Snackbar.make(it, "キャンセルしました。", Snackbar.LENGTH_SHORT).show() })
                    dialog.show(parentFragmentManager, "delete_dialog")
                }
                binding.dateButton.setOnClickListener {
                    DateDialog { date ->
                        binding.dateEdit.setText(date)
                    }.show(parentFragmentManager, "date_dialog")
                }
                binding.timeButton.setOnClickListener {
                    TimeDialog { time ->
                        binding.timeEdit.setText(time)
                    }.show(parentFragmentManager, "time_dialog")
                }
            }
        })
    }

    private fun saveEnroll(view: View) {
        when (args.enrollId) {
            null -> {
                //realm = Realm.getInstance(config)
                //Realm.getInstanceAsync(config, object : Realm.Callback() {
                //    override fun onSuccess(realm: Realm) {
                //        Log.v(
                //                "EXAMPLE",
                //                "Successfully opened a realm with reads and writes allowed on the UI thread."
                //        )
                realm.executeTransaction { transactionRealm ->
                val maxId = realm.where<Schedule>().count()
                //val maxId = realm.where<Schedule>().max()
                val nextId = (maxId.toLong() ?: 0L) + 1L
                val schedule = realm.createObject<Schedule>(nextId)
                val date = "${binding.dateEdit.text} ${binding.timeEdit.text}".toDate()
                if (date != null) schedule.date = date
                schedule.personname = binding.PersonNameEdit.text.toString()
                schedule.detail = binding.detailEdit.text.toString()
                //realm.executeTransaction { transactionRealm ->
                     //       transactionRealm.insert(schedule)
                        }
                Snackbar.make(view, "追加しました", Snackbar.LENGTH_SHORT)
                                .setAction("戻る") { findNavController().popBackStack() }
                                .setActionTextColor(Color.YELLOW)
                                .show()
            }
              else ->{
                   realm.executeTransaction { db: Realm ->
                       val schedule = db.where<Schedule>().equalTo("_id",args.enrollId).findFirst()
                        val date = "${binding.dateEdit.text} ${binding.timeEdit.text}".toDate()
                        if (date != null) schedule?.date = date
                        schedule?.personname = binding.PersonNameEdit.text.toString()
                        schedule?.detail = binding.detailEdit.text.toString()
               }
                   Snackbar.make(view, "修正しました", Snackbar.LENGTH_SHORT)
                       .setAction("戻る"){findNavController().popBackStack()}
                       .setActionTextColor(Color.YELLOW)
                       .show()
        }
    }
}

    private fun deleteEnroll(view: View) {
        realm.executeTransaction { db: Realm ->
            db.where<Schedule>().equalTo("_id", args.enrollId)?.findFirst()?.deleteFromRealm()
        }
        Snackbar.make(view, "削除しました",Snackbar.LENGTH_SHORT).setActionTextColor(Color.YELLOW).show()
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun String.toDate(pattern: String = "yyyy/MM/dd HH:mm"): Date?{
        return try {
            SimpleDateFormat(pattern).parse(this)
        } catch (e: IllegalArgumentException) {
            return null
        } catch (e: ParseException) {
            return null
        }
    }

}