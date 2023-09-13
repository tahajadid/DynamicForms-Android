package com.example.dynamicforms.formActivity

import com.example.dynamicforms.formActivity.util.models.JSONModel
import com.example.dynamicforms.formActivity.util.models.ListModel

object FormConstants {
    const val TYPE_TEXT = 1
    const val TYPE_CUSTOM_EDITTEXT = 2
    const val TYPE_SPINNER = 3
    const val TYPE_DATE = 5
    const val TYPE_SPACE = 6
    const val TYPE_CHECKBOX = 7
    const val TYPE_SUBMIT_BUTTON = 10
    const val TYPE_LIST = 11

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
