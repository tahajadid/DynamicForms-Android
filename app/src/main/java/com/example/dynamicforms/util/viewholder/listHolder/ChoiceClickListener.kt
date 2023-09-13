package com.example.dynamicforms.util.viewholder.listHolder

import com.example.dynamicforms.util.models.Choice

interface ChoiceClickListener {
    fun onItemClick(position: Int, choice: Choice)
}
