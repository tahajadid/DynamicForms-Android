package com.example.dynamicforms.util.viewholder.editTextHolder

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.util.models.JSONModel
import com.example.dynamicforms.util.sigleton.DataValueHashMap
import com.google.android.material.textfield.TextInputLayout

class EditTextViewHolder(itemView: View, jsonModelList: List<JSONModel>) :
    RecyclerView.ViewHolder(itemView) {
    var layoutEdittext: TextInputLayout

    init {
        layoutEdittext = itemView.findViewById(R.id.layout_edittext)
        layoutEdittext.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                if (adapterPosition == -1) {
                    return
                }
                DataValueHashMap.put(
                    jsonModelList[adapterPosition].id.toString(),
                    editable.toString())
                if (editable.length > 0 && layoutEdittext.isErrorEnabled) {
                    layoutEdittext.isErrorEnabled = false
                }
            }
        })
    }
}
