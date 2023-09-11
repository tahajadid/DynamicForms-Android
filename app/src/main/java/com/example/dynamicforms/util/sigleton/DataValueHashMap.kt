package com.example.dynamicforms.util.sigleton

import com.example.dynamicforms.util.FormConstants

object DataValueHashMap {

    var dataValueHashMap: HashMap<String, String>? = null
    fun init(): Boolean {
        dataValueHashMap = HashMap()
        return true
    }

    fun put(_id: String, value: String): Boolean {
        dataValueHashMap!![_id] = value
        return true
    }

    fun getValue(_id: String?): String {
        var value = FormConstants.EMPTY_STRING
        if (dataValueHashMap == null) {
            return value
        }

        dataValueHashMap!!.forEach {
            if (it.key.equals(_id)) value = it.value
        }

        return value
    }

    fun remove(_id: String): Boolean {
        dataValueHashMap!!.remove(_id)
        return true
    }

    fun clear(): Boolean {
        dataValueHashMap!!.clear()
        return true
    }
}
