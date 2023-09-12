package com.example.dynamicforms.util.viewholder.listHolder

import com.example.dynamicforms.data.Choice

interface ChoiceClickListener {
    fun onItemClick(position: Int, choice: Choice)
}