package com.example.dynamicforms.util.viewholder

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.util.FormConstants
import com.example.dynamicforms.util.models.JSONModel
import com.example.dynamicforms.util.sigleton.DataValueHashMap

class RadioViewHolder(itemView: View, jsonModelList: List<JSONModel>) :
    RecyclerView.ViewHolder(itemView) {
    var txtRadio: TextView
    var rGroup: RadioGroup

    init {
        txtRadio = itemView.findViewById(R.id.txt_radio)
        rGroup = itemView.findViewById(R.id.rGroup)
        rGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            if (adapterPosition == -1) {
                return@OnCheckedChangeListener
            }
            DataValueHashMap.put(
                jsonModelList[adapterPosition].id.toString(),
                if (rGroup.checkedRadioButtonId == -1) FormConstants.EMPTY_STRING else (itemView.findViewById<View>(
                    rGroup.checkedRadioButtonId) as RadioButton).text.toString())
            if (rGroup.checkedRadioButtonId != -1) {
                for (j in 0 until radioGroup.childCount) {
                    (rGroup.getChildAt(j) as RadioButton).error = null
                }
            }
            if (itemView.rootView.findFocus() != null) {
                itemView.rootView.findFocus().clearFocus()
            }
        })
    }
}
