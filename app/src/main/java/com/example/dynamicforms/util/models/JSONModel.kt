package com.example.dynamicforms.util.models

data class JSONModel(
    val id: String? = null,
    val text: String? = null,
    val type: Int? = null,
    val inputType: String? = null,
    val selectedValue: String? = null,
    val hint: String? = null,
    val isEditable: Boolean? = null,
    val maxLength: Int? = null,
    val isRequired: Boolean? = null,
    val list: List<ListModel>? = null,
)
