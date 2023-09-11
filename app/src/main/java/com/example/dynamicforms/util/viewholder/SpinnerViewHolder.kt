package com.example.dynamicforms.util.viewholder

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.util.models.JSONModel
import com.example.dynamicforms.util.sigleton.DataValueHashMap

class SpinnerViewHolder(itemView: View, jsonModelList: List<JSONModel>) :
    RecyclerView.ViewHolder(itemView) {
    var txtSpinner: TextView
    var spinner: Spinner

    init {
        txtSpinner = itemView.findViewById(R.id.txt_spinner)
        spinner = itemView.findViewById(R.id.spinner)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                if (adapterPosition == -1) {
                    return
                }
                DataValueHashMap.put(
                    jsonModelList[adapterPosition].id.toString(),
                    spinner.selectedItem.toString())
                if (itemView.rootView.findFocus() != null) {
                    itemView.rootView.findFocus().clearFocus()
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }
}