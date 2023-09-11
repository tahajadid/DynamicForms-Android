package com.example.dynamicforms.data

data class FormResponse (
    var idForm: String? = null,
    var listOfItems : MutableList<ViewItem> = arrayListOf()
)