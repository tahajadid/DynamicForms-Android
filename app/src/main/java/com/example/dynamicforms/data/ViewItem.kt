package com.example.dynamicforms.data

data class ViewItem (
    var Id : String? = null,
    var text : String? = null,
    var isRequired : Boolean? = null,
    var listOfChoices : ArrayList<Choice> = arrayListOf(),
    var type : Int? = null
)