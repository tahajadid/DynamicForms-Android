package com.example.dynamicforms.util.validate

import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.util.FormConstants
import com.example.dynamicforms.util.models.JSONModel
import com.example.dynamicforms.util.sigleton.DataValueHashMap
import com.example.dynamicforms.util.viewholder.CheckboxViewHolder
import com.example.dynamicforms.util.viewholder.EditTextViewHolder
import com.example.dynamicforms.util.viewholder.RadioViewHolder


object CheckFieldValidations {
    fun isFieldsValidated(recyclerView: RecyclerView, jsonModelList: List<JSONModel>): Boolean {
        val isValidated = booleanArrayOf(true)
        for (i in jsonModelList.indices) {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(i)
            if (viewHolder?.itemView != null) {
                if (viewHolder is EditTextViewHolder) {
                    viewHolder.layoutEdittext.isErrorEnabled = false
                } else if (viewHolder is RadioViewHolder) {
                    for (j in 0 until viewHolder.rGroup.childCount) {
                        (viewHolder.rGroup.getChildAt(j) as RadioButton).error =
                            null
                    }
                } else if (viewHolder is CheckboxViewHolder) {
                    viewHolder.checkBox.error = null
                }
            }
        }
        for (i in jsonModelList.indices) {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(i)
            val jsonModel = jsonModelList[i]
            val fieldValue = DataValueHashMap.getValue(jsonModel.id)
            val EMPTY_STRING = ""
            if (jsonModel.isRequired != null && jsonModel.isRequired) {
                if (viewHolder?.itemView != null) {
                    if (jsonModel.type == FormConstants.TYPE_EDITTEXT
                        && fieldValue.equals(EMPTY_STRING, ignoreCase = true)) {
                        (viewHolder as EditTextViewHolder).layoutEdittext.isErrorEnabled = true
                        viewHolder.layoutEdittext.error = FormConstants.FIELD_REQUIRED
                        recyclerView.smoothScrollToPosition(i)
                        isValidated[0] = false
                    } else if (jsonModel.type == FormConstants.TYPE_CHECKBOX
                        && fieldValue.equals(EMPTY_STRING, ignoreCase = true)) {
                        (viewHolder as CheckboxViewHolder).checkBox.error =
                            FormConstants.FIELD_REQUIRED
                        recyclerView.smoothScrollToPosition(i)
                        isValidated[0] = false
                    } else if (jsonModel.type == FormConstants.TYPE_RADIO
                        && fieldValue.equals(EMPTY_STRING, ignoreCase = true)) {
                        for (j in 0 until (viewHolder as RadioViewHolder).rGroup.childCount) {
                            (viewHolder.rGroup.getChildAt(j) as RadioButton).error =
                                FormConstants.FIELD_REQUIRED
                        }
                        recyclerView.smoothScrollToPosition(i)
                        isValidated[0] = false
                    }
                }
            }
        }
        return isValidated[0]
    }
}