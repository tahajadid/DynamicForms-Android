package com.example.dynamicforms

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.util.CommonUtils
import com.example.dynamicforms.util.formAdapter.FormAdapter
import com.example.dynamicforms.util.interfaces.JsonToFormClickListener
import com.example.dynamicforms.util.models.JSONModel
import com.example.dynamicforms.util.sigleton.DataValueHashMap
import com.example.dynamicforms.util.validate.CheckFieldValidations
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(), JsonToFormClickListener {

    var recyclerView: RecyclerView? = null
    var mAdapter: FormAdapter? = null
    var jsonModelList: ArrayList<JSONModel> = ArrayList<JSONModel>()

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
    }

    /**
     * function to read data from json file
     */
    private fun fetchData() {
        val json: String = CommonUtils.loadJSONFromAsset(applicationContext, DATA_JSON_PATH).toString()
        var jsonModelList1: ArrayList<JSONModel>
        jsonModelList1 = Gson().fromJson(json, object : TypeToken<List<JSONModel?>?>() {}.type)
        jsonModelList.addAll(jsonModelList1)
        mAdapter!!.notifyDataSetChanged()
    }

    /**
     * function to set the data to the adapter of our list
     */
    private fun initRecyclerView() {
        mAdapter = FormAdapter(jsonModelList, this, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = mAdapter
    }

    override fun onAddAgainButtonClick() {
        Toast.makeText(this, "Add again button click", Toast.LENGTH_SHORT).show()
    }

    override fun onSubmitButtonClick() {
        if (!CheckFieldValidations.isFieldsValidated(recyclerView!!, jsonModelList)) {
            Toast.makeText(this, "Validation Failed", Toast.LENGTH_SHORT).show()
            return
        }
    }
}
