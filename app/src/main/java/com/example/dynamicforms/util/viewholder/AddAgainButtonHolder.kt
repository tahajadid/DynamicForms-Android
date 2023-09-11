package com.example.dynamicforms.util.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.util.interfaces.JsonToFormClickListener
import com.example.dynamicforms.util.models.JSONModel
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class AddAgainButtonHolder(
    itemView: View,
    jsonModelList: List<JSONModel?>?,
    jsonToFormClickListener: JsonToFormClickListener,
) :
    RecyclerView.ViewHolder(itemView) {
    var btnAddAgain: ExtendedFloatingActionButton

    init {
        btnAddAgain = itemView.findViewById(R.id.btn_add_again)
        btnAddAgain.setOnClickListener(View.OnClickListener {
            if (adapterPosition == -1) {
                return@OnClickListener
            }
            jsonToFormClickListener.onAddAgainButtonClick()
        })
    }
}