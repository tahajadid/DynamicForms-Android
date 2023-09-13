package com.example.dynamicforms.formActivity.util

import android.content.Context
import android.util.Log
import java.io.IOException
import java.nio.charset.Charset

object CommonUtils {
    private const val TAG = "CommonUtils"
    fun loadJSONFromAsset(context: Context, fileName: String?): String? {
        var json: String? = null
        json = try {
            val `is` = context.assets.open(fileName!!)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            Log.d(TAG, "Exception Occurred : " + ex.message)
            return null
        }
        return json
    }
}
