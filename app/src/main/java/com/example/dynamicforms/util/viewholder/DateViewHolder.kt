package com.example.dynamicforms.util.viewholder

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.view.View
import android.widget.DatePicker
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.util.models.JSONModel
import com.example.dynamicforms.util.sigleton.DataValueHashMap
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class DateViewHolder(itemView: View, jsonModelList: List<JSONModel>) :
    RecyclerView.ViewHolder(itemView) {
    var layoutDate: TextInputLayout
    var etdate: TextInputEditText

    init {
        layoutDate = itemView.findViewById<View>(R.id.layout_date) as TextInputLayout
        etdate = itemView.findViewById<View>(R.id.et_date) as TextInputEditText
        layoutDate.setOnClickListener { view: View? ->
            onDateViewClick(jsonModelList,
                adapterPosition)
        }
        etdate.setOnClickListener { view: View? ->
            onDateViewClick(jsonModelList,
                adapterPosition)
        }
    }

    fun onDateViewClick(jsonModelList: List<JSONModel>, position: Int) {
        if (adapterPosition == -1) {
            return
        }
        val myCalendar = Calendar.getInstance()
        val date =
            OnDateSetListener { view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = monthOfYear
                myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                val myFormat = "dd/MM/yyyy" // your format
                val sdf =
                    SimpleDateFormat(myFormat, Locale.getDefault())
                layoutDate.editText!!.setText(sdf.format(myCalendar.time))
                DataValueHashMap.put(jsonModelList[position].id.toString(),
                    sdf.format(myCalendar.time))
            }
        DatePickerDialog(itemView.context, date,
            myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
            myCalendar[Calendar.DAY_OF_MONTH]).show()
    }
}

