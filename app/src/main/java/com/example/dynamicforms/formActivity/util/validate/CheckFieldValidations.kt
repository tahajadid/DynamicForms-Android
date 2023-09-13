package com.example.dynamicforms.formActivity.util.validate

import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.formActivity.FormConstants
import com.example.dynamicforms.formActivity.util.models.JSONModel
import com.example.dynamicforms.formActivity.util.sigleton.DataValueHashMap
import com.example.dynamicforms.formActivity.util.viewholder.checkBoxHolder.CheckboxViewHolder
import com.example.dynamicforms.formActivity.util.viewholder.customEditTextHolder.CustomEditTextHolder

object CheckFieldValidations {
    fun isFieldsValidated(recyclerView: RecyclerView, jsonModelList: List<JSONModel>): Boolean {
        val isValidated = booleanArrayOf(true)
        for (i in jsonModelList.indices) {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(i)
            if (viewHolder?.itemView != null) {
                if (viewHolder is CustomEditTextHolder) {
                    viewHolder.layoutEdittext.isErrorEnabled = false
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
                    if (jsonModel.type == FormConstants.TYPE_CUSTOM_EDITTEXT &&
                        fieldValue.equals(EMPTY_STRING, ignoreCase = true)
                    ) {
                        (viewHolder as CustomEditTextHolder).layoutEdittext.isErrorEnabled = true
                        viewHolder.layoutEdittext.error = FormConstants.FIELD_REQUIRED
                        recyclerView.smoothScrollToPosition(i)
                        isValidated[0] = false
                    } else if (jsonModel.type == FormConstants.TYPE_CHECKBOX &&
                        fieldValue.equals(EMPTY_STRING, ignoreCase = true)
                    ) {
                        (viewHolder as CheckboxViewHolder).checkBox.error =
                            FormConstants.FIELD_REQUIRED
                        recyclerView.smoothScrollToPosition(i)
                        isValidated[0] = false
                    }
                }
            }
        }
        return isValidated[0]
    }
}
