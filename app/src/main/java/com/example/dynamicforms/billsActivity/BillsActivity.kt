package com.example.dynamicforms.billsActivity // ktlint-disable package-name

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.billsActivity.adapters.BillSectionAdapter
import com.example.dynamicforms.billsActivity.data.Merchants
import com.example.dynamicforms.billsActivity.data.MerchantsHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BillsActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var billSectionAdapter: BillSectionAdapter? = null
    var jsonModelList: ArrayList<Merchants> = ArrayList()

    var merchantsHolderList: ArrayList<MerchantsHolder> = ArrayList()

    private val DATA_JSON_PATH = "custom_response.json"

    companion object {
        lateinit var activityInstance: BillsActivity
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bills)

        recyclerView = findViewById(R.id.recyclerview)
        fetchData()
        initRecyclerView()

        activityInstance = this
    }

    // get data from json response
    private fun fetchData() {
        val json: String = com.example.dynamicforms.formActivity.util.CommonUtils.loadJSONFromAsset(applicationContext, DATA_JSON_PATH).toString()
        var jsonModelList1: ArrayList<Merchants> = Gson().fromJson(json, object : TypeToken<List<Merchants?>?>() {}.type)
        jsonModelList.addAll(jsonModelList1)
    }

    /***
     * function to init bills list
     */
    private fun initRecyclerView() {
        sortList(jsonModelList)
        billSectionAdapter = BillSectionAdapter(
            this,
            merchantsHolderList,
        )
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = billSectionAdapter
    }

    /***
     * function to sort the existing list
     */
    private fun sortList(jsonModelList: ArrayList<Merchants>) {
        // group the list of merchants
        var newList = jsonModelList.groupBy {
            it.names?.fr?.firstOrNull()?.toUpperCase()
        }

        // fill the list of billHolder
        newList.forEach {
            merchantsHolderList.add(MerchantsHolder(it.key.toString(), it.value as ArrayList<Merchants>))
        }

        // sort it by the first letter
        merchantsHolderList.sortBy { it.title }
    }
}
