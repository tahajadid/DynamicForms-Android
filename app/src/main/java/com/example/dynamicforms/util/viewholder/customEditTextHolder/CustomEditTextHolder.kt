package com.example.dynamicforms.util.viewholder.customEditTextHolder

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.util.models.JSONModel
import com.example.dynamicforms.util.sigleton.DataValueHashMap
import com.google.android.material.textfield.TextInputLayout

class CustomEditTextHolder(itemView: View, jsonModelList: List<JSONModel>) :
    RecyclerView.ViewHolder(itemView) {
    var layoutEdittext: TextInputLayout
    var editTextIcon: ImageView
    var errorText: TextView
    var errorIcon: ImageView

    init {
        layoutEdittext = itemView.findViewById(R.id.editText_layout)
        editTextIcon = itemView.findViewById(R.id.editText_icon)
        errorText = itemView.findViewById(R.id.error_text)
        errorIcon = itemView.findViewById(R.id.error_icon)

        hideErrorElements()
        editTextIcon.visibility = View.GONE

        layoutEdittext.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }
            override fun afterTextChanged(editable: Editable) {
                if (adapterPosition == -1) {
                    return
                }
                DataValueHashMap.put(
                    jsonModelList[adapterPosition].id.toString(),
                    editable.toString(),
                )
                if (editable.length > 0 && layoutEdittext.isErrorEnabled) {
                    layoutEdittext.isErrorEnabled = false
                }
            }
        })
    }

    /**
     * function to hide the error section
     */
    private fun hideErrorElements() {
        errorText.visibility = View.GONE
        errorIcon.visibility = View.GONE
    }

    /**
     * dynamic function to show the message error and the icon
     */
    private fun showErrorElement(errorTextContent: String, iconIsVisible: Boolean) {
        if (iconIsVisible) errorIcon.visibility = View.VISIBLE else errorIcon.visibility = View.GONE
        errorText.visibility = View.VISIBLE
        errorText.text = errorTextContent
    }
}
