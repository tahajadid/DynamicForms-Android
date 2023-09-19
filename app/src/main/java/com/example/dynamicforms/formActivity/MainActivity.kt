package com.example.dynamicforms.formActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.formActivity.util.interfaces.JsonToFormClickListener
import com.example.dynamicforms.formActivity.util.models.JSONModel
import com.example.dynamicforms.formActivity.util.sigleton.DataValueHashMap
import com.example.dynamicforms.formActivity.util.validate.CheckFieldValidations
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(), JsonToFormClickListener {

    var recyclerView: RecyclerView? = null
    var mAdapter: com.example.dynamicforms.formActivity.util.formAdapter.FormAdapter? = null
    var jsonModelList: ArrayList<JSONModel> = ArrayList()

    private val DATA_JSON_PATH = "data.json"

    companion object {
        lateinit var activityInstance: MainActivity
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Init Library HashMap
        DataValueHashMap.init()

        recyclerView = findViewById(R.id.recyclerview)
        initRecyclerView()
        fetchData()
        activityInstance = this

        val intent = getIntent()
        val str = intent.getIntExtra("idMerchant",0)
    }

    override fun onResume() {
        super.onResume()
    }

    /**
     * function to read data from json file
     */
    private fun fetchData() {
        val json: String = com.example.dynamicforms.formActivity.util.CommonUtils.loadJSONFromAsset(applicationContext, DATA_JSON_PATH).toString()
        var jsonModelList1: ArrayList<JSONModel> = Gson().fromJson(json, object : TypeToken<List<JSONModel?>?>() {}.type)
        jsonModelList.addAll(jsonModelList1)
        mAdapter!!.notifyDataSetChanged()
    }

    /**
     * function to set the data to the adapter of our list
     */
    private fun initRecyclerView() {
        mAdapter = com.example.dynamicforms.formActivity.util.formAdapter.FormAdapter(
            jsonModelList,
            this,
        )
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = mAdapter
    }

    override fun onSubmitButtonClick() {
        if (!CheckFieldValidations.isFieldsValidated(recyclerView!!, jsonModelList)) {
            Toast.makeText(this, "Validation Failed", Toast.LENGTH_SHORT).show()
            return
        }
    }
}
