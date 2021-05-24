package com.example.myscheduler

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class ConfirmDialog(private val message: String,
private val okLabel: String,
private val okSelected: () -> Unit,
private val cancelLabel: String,
private val cancelSelected: () -> Unit)
    : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage(message)
        builder.setPositiveButton(okLabel){
            dialog, which -> okSelected()
        }
        builder.setNegativeButton(cancelLabel){
            dialog, which -> cancelSelected()
        }
        return builder.create()
    }
    }

class ConfirmDialog_ath(private val message1: String,
                    private val okLabel1: String,
                    private val okSelected1: () -> Unit,
                    private val cancelLabel1: String,
                    private val cancelSelected1: () -> Unit,
                    private val message2: String,
                    private val okLabel2: String,
                    private val okSelected2: () -> Unit,
                    private val cancelLabel2: String,
                    private val cancelSelected2: () -> Unit)
    : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage(message1)
        builder.setPositiveButton(okLabel1){
                dialog, which -> okSelected1()
        }
        builder.setNegativeButton(cancelLabel1){
                dialog, which -> cancelSelected1()
        }
        builder.setMessage(message2)
        builder.setPositiveButton(okLabel2){
                dialog, which -> okSelected2()
        }
        builder.setNegativeButton(cancelLabel2){
                dialog, which -> cancelSelected2()
        }
        return builder.create()
    }
}

class DateDialog(private val onSelected: (String) -> Unit)
    : DialogFragment() , DatePickerDialog.OnDateSetListener{

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val date = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireActivity(), this,year,month,date)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        onSelected("$year/${month + 1}/$dayOfMonth")
    }
    }

class TimeDialog(private val onSelected: (String) -> Unit)
    : DialogFragment(), TimePickerDialog.OnTimeSetListener{

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        return TimePickerDialog(context, this, hour, minute, true)
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        onSelected("%1$02d:%2$02d".format(hourOfDay, minute))
    }
    }
