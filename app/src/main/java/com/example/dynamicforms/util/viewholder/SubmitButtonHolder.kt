package com.example.dynamicforms.util.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.util.interfaces.JsonToFormClickListener
import com.example.dynamicforms.util.models.JSONModel
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class SubmitButtonHolder(
    itemView: View,
    jsonModelList: List<JSONModel?>?,
    mSubmitBtnListener: JsonToFormClickListener,
) :
    RecyclerView.ViewHolder(itemView) {
    var btnSubmit: ExtendedFloatingActionButton

    init {
        btnSubmit = itemView.findViewById(R.id.btn_submit)
        btnSubmit.setOnClickListener(View.OnClickListener {
            if (adapterPosition == -1) {
                return@OnClickListener
            }
            if (itemView.rootView.findFocus() != null) {
                itemView.rootView.findFocus().clearFocus()
            }
            mSubmitBtnListener.onSubmitButtonClick()
        })
    }


}
