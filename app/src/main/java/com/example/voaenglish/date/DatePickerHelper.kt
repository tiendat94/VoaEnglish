package com.example.voaenglish.date

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import com.example.voaenglish.R
import java.util.*

class DatePickerHelper(private var context: Context, isSpinnerType: Boolean = false) {
    private lateinit var dialog: DatePickerDialog
    private var callBackDate: CallBackDate? = null

    private var listener = DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
        callBackDate?.onDateSelected(dayOfMonth, month, year)
    }

    init {
        val style = if (isSpinnerType) R.style.SpinnerDatePickerDialog else 0
        val cal = Calendar.getInstance()
        dialog = DatePickerDialog(context, style, listener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
    }

    fun showDialog(dayorMonth: Int?, month: Int?, year: Int?, callBackDate: CallBackDate?) {
        this.callBackDate = callBackDate
        year?.let { dialog.datePicker.init(it, month!!, dayorMonth!!, null) }
        dialog.show()
    }

    fun setMinDate(minDate: Long) {
        dialog?.datePicker?.minDate = minDate
    }

    fun setMaxDate(maxDate: Long) {
        dialog?.datePicker?.maxDate = maxDate
    }

    interface CallBackDate {
        fun onDateSelected(dayofMonth: Int?, month: Int?, year: Int?)
    }
}