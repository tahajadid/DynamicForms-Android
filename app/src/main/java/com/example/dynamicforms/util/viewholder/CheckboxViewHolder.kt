package com.example.dynamicforms.util.viewholder

import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.util.models.JSONModel
import com.example.dynamicforms.util.sigleton.DataValueHashMap

class CheckboxViewHolder(itemView: View, jsonModelList: List<JSONModel>) :
    RecyclerView.ViewHolder(itemView) {
    var checkBox: CheckBox

    init {
        checkBox = itemView.findViewById(R.id.checkbox)
        checkBox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            if (adapterPosition == -1) {
                return@OnCheckedChangeListener
            }
            if (b) {
                DataValueHashMap.put(
                    jsonModelList[adapterPosition].id.toString(),
                    "1")
                checkBox.error = null
            } else {
                DataValueHashMap.put(
                    jsonModelList[adapterPosition].id.toString(),
                    "0")
            }
            if (itemView.rootView.findFocus() != null) {
                itemView.rootView.findFocus().clearFocus()
            }
        })
    }
}
