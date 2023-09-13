package com.example.dynamicforms.formActivity.util.viewholder.listHolder

import com.example.dynamicforms.formActivity.util.models.Choice

interface ChoiceClickListener {
    fun onItemClick(position: Int, choice: Choice)
}
