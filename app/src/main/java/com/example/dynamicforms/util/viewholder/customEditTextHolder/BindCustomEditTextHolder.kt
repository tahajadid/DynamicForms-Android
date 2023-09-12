package com.example.dynamicforms.util.viewholder.customEditTextHolder

import android.text.InputFilter
import android.text.InputType
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import com.example.dynamicforms.util.FormConstants
import com.example.dynamicforms.util.models.JSONModel
import com.example.dynamicforms.util.sigleton.DataValueHashMap

object BindCustomEditTextHolder {

    fun bindCustomEditText(jsonModelList: ArrayList<JSONModel>, holder: CustomEditTextHolder, position: Int) {
        val jsonModel = jsonModelList[position]
        holder.layoutEdittext.editText!!.setOnEditorActionListener { v, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_NEXT) {
                Log.i("KEYPADPressed", position.toString())
            }
            false
        }
        holder.layoutEdittext.setHint(jsonModel.text)
        //
        if (jsonModel.maxLength != null) {
            holder.layoutEdittext.editText!!.filters = arrayOf<InputFilter>(
                InputFilter.LengthFilter(
                    jsonModel.maxLength,
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
        if (jsonModel.inputType != null && jsonModel.inputType.equals(FormConstants.INPUT_TYPE_NUMBER)) {
            holder.layoutEdittext.editText!!.inputType = InputType.TYPE_CLASS_NUMBER
        } else if (jsonModel.inputType != null && jsonModel.inputType.equals(FormConstants.INPUT_TYPE_NUMBER_DECIMAL)) {
            holder.layoutEdittext.editText!!.inputType =
                InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        } else {
            holder.layoutEdittext.editText!!.inputType = InputType.TYPE_CLASS_TEXT
        }
        //
        holder.layoutEdittext.isEnabled = !(jsonModel.isEditable != null && !jsonModel.isEditable)
        //
        if (!DataValueHashMap.getValue(jsonModel.id).isEmpty()) {
            holder.layoutEdittext.editText!!.setText(DataValueHashMap.getValue(jsonModel.id))
        } else {
            holder.layoutEdittext.editText!!.setText(FormConstants.EMPTY_STRING)
        }
    }
}