package com.example.dynamicforms

import com.example.dynamicforms.util.models.JSONModel
import com.example.dynamicforms.util.models.ListModel

object Constants {

    const val TYPE_TEXT = "TEXT_VIEW"
    const val TYPE_EDITTEXT = "EDIT_TEXT"
    const val TYPE_SPINNER = "SPINNER"
    const val TYPE_RADIO = "RADIO_BUTTON"
    const val TYPE_DATE = "DATE"
    const val TYPE_SPACE = "SPACE"
    const val TYPE_CHECKBOX = "CHECKBOX"

    const val EMPTY_STRING = ""
    const val INPUT_TYPE_NUMBER = "numbers"
    const val INPUT_TYPE_NUMBER_DECIMAL = "numbers_decimal"

    const val FIELD_REQUIRED = "*Required"

    val ListOfModel = arrayListOf(
        JSONModel(),
        JSONModel(
            "edittext_11",
            "edittext_11",
            2,
            "edittext_21",
            "edittext_21",
            "edittext_12",
            true,
            4,
            true,
            listOf(),
        ),
        JSONModel(
            "edittext_1",
            "edittext_1",
            11,
            "edittext_1",
            "edittext_1",
            "edittext_1",
            true,
            4,
            true,
            listOf(),
        ),
        JSONModel(),
        JSONModel(
            "radio_group_1",
            "Hi! I'm radio group",
            4,
            "",
            "",
            "",
            false,
            4,
            true,
            listOf(
                ListModel(
                    0,
                    "Radio 1",
                ),
                ListModel(
                    1,
                    "Radio 2",
                ),
                ListModel(
                    2,
                    "Radio 3",
                ),
                ListModel(
                    3,
                    "Radio 4",
                ),
            ),
        ),
    )
}
