package com.example.dynamicforms.util.viewholder

import com.example.dynamicforms.data.Choice

interface ChoiceClickListener {
    fun onItemClick(position: Int, choice: Choice)
}