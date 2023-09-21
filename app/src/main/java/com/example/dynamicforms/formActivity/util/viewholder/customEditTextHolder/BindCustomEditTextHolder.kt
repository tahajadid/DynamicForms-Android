package com.example.dynamicforms.formActivity.util.viewholder.customEditTextHolder

import android.text.InputFilter
import android.text.InputType
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import com.example.dynamicforms.billsActivity.data.MerchantServiceFields
import com.example.dynamicforms.formActivity.FormConstants
import com.example.dynamicforms.formActivity.util.sigleton.DataValueHashMap

object BindCustomEditTextHolder {

    fun bindCustomEditText(fieldList: ArrayList<MerchantServiceFields>, holder: CustomEditTextHolder, position: Int) {
        val jsonModel = fieldList[position]
        holder.layoutEdittext.editText!!.setOnEditorActionListener { v, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_NEXT) {
                Log.i("KEYPADPressed", position.toString())
            }
            false
        }
        if(jsonModel.names?.fr.isNullOrEmpty()) holder.layoutEdittext.setHint(jsonModel.names!!.dflt.toString())
        else holder.layoutEdittext.setHint(jsonModel.names!!.fr.toString())

        //
        if (jsonModel.maxLength != null) {
            holder.layoutEdittext.editText!!.filters = arrayOf<InputFilter>(
                InputFilter.LengthFilter(
                    jsonModel.maxLength!!,
                ),
            )
        } else {
            holder.layoutEdittext.editText!!.filters = arrayOf<InputFilter>(
                InputFilter.LengthFilter(
                    100,
                ),
            )
        }

        //
        if (jsonModel.dataType != null && jsonModel.dataType.equals(FormConstants.INPUT_TYPE_NUMBER)) {
            holder.layoutEdittext.editText!!.inputType = InputType.TYPE_CLASS_NUMBER
        } else if (jsonModel.dataType != null && jsonModel.dataType.equals(FormConstants.INPUT_TYPE_NUMBER_DECIMAL)) {
            holder.layoutEdittext.editText!!.inputType =
                InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        } else {
            holder.layoutEdittext.editText!!.inputType = InputType.TYPE_CLASS_TEXT
        }
        //
        //holder.layoutEdittext.isEnabled = !(jsonModel.isEditable != null && !jsonModel.isEditable)
        //
        if (!DataValueHashMap.getValue(jsonModel.code.toString()).isEmpty()) {
            holder.layoutEdittext.editText!!.setText(DataValueHashMap.getValue(jsonModel.code.toString()))
        } else {
            holder.layoutEdittext.editText!!.setText(FormConstants.EMPTY_STRING)
        }
    }
}