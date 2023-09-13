package com.example.dynamicforms.formActivity.util.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.formActivity.util.models.JSONModel

class TextViewHolder(itemView: View, jsonModelList: List<JSONModel?>?) : RecyclerView.ViewHolder(itemView) {

    var txtHead: TextView

    init {
        txtHead = itemView.findViewById(R.id.txtHead)
    }
}