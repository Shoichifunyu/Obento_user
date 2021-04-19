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
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class EnrollFragment : Fragment() {
    private var _binding: FragmentEnrollBinding? = null
    private val binding get() = _binding!!

    private lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realm = Realm.getDefaultInstance()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentEnrollBinding.inflate(inflater,container,false)
        return binding.root
    }
    private val args: EnrollFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (args.enrollId != -1L){
            val schedule = realm.where<Schedule>()
               .equalTo("id", args.enrollId).findFirst()
            binding.dateEdit.setText(DateFormat.format("yyyy/MM/dd",schedule?.date))
            binding.timeEdit.setText(DateFormat.format("HH:mm",schedule?.date))
            binding.PersonNameEdit.setText(schedule?.personname)
            binding.detailEdit.setText(schedule?.detail)
        }
        binding.save.setOnClickListener{saveEnroll(it)}
    }

    private fun saveEnroll(view: View) {
        when (args.enrollId) {
            -1L -> {
                realm.executeTransaction { db: Realm ->
                    val maxId = db.where<Schedule>().max("id")
                    val nextId = (maxId?.toLong() ?: 0L) + 1L
                    val schedule = db.createObject<Schedule>(nextId)
                    val date = "${binding.dateEdit.text} ${binding.timeEdit.text}".toDate()
                    if (date != null) schedule.date = date
                    schedule.personname = binding.PersonNameEdit.text.toString()
                    schedule.detail = binding.detailEdit.text.toString()
                }
                Snackbar.make(view,"追加しました", Snackbar.LENGTH_SHORT)
                    .setAction("戻る"){findNavController().popBackStack()}
                    .setActionTextColor(Color.YELLOW)
                    .show()
           }
               else ->{
                    realm.executeTransaction { db: Realm ->
                        val schedule = db.where<Schedule>().equalTo("id",args.enrollId).findFirst()
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