package com.example.dynamicforms.util.adapters

import com.example.dynamicforms.util.FormConstants
import com.example.dynamicforms.util.models.JSONModel
import com.example.dynamicforms.util.sigleton.DataValueHashMap
import com.example.dynamicforms.util.viewholder.CheckboxViewHolder

object BindCheckBox {

    fun bindCheckBox(jsonModelList: ArrayList<JSONModel>, holder: CheckboxViewHolder, position: Int) {
        val jsonModel = jsonModelList[position]
        holder.checkBox.setText(jsonModel.text)
        holder.checkBox.isChecked = DataValueHashMap.getValue(jsonModel.id) == "1"
        if (position != -1 && DataValueHashMap.getValue(jsonModelList[position].id)
                .equals(FormConstants.EMPTY_STRING, ignoreCase = true)
        ) {
            DataValueHashMap.put(
                jsonModelList[position].id.toString(),
                if (!holder.checkBox.isChecked) FormConstants.EMPTY_STRING else holder.checkBox.text.toString())
        }
    }

}