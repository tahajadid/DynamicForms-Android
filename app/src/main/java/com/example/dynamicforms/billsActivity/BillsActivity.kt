package com.example.dynamicforms.billsActivity // ktlint-disable package-name

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.formActivity.data.Merchants
import com.example.dynamicforms.formActivity.data.MerchantsHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BillsActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var billSectionAdapter: BillSectionAdapter? = null
    var jsonModelList: ArrayList<Merchants> = ArrayList()

    var finalList: ArrayList<MerchantsHolder> = ArrayList()

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

        val arrayList = arrayListOf("apple", "aanana", "cherry", "date", "grape", "apricot")

        val groupedByFirstAlphabet = arrayList.groupBy { it.firstOrNull()?.toUpperCase() }

        Log.d("ResultLog","Result groupedByFirstAlphabet : "+groupedByFirstAlphabet.toString())

        activityInstance = this
    }

    private fun fetchData() {
        val json: String = com.example.dynamicforms.formActivity.util.CommonUtils.loadJSONFromAsset(applicationContext, DATA_JSON_PATH).toString()
        var jsonModelList1: ArrayList<Merchants> = Gson().fromJson(json, object : TypeToken<List<Merchants?>?>() {}.type)
        jsonModelList.addAll(jsonModelList1)
    }

    private fun initRecyclerView() {
        sortList(jsonModelList)
        billSectionAdapter = BillSectionAdapter(
            this,
            finalList,
        )
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = billSectionAdapter
        billSectionAdapter!!.notifyDataSetChanged()
    }

    private fun sortList(jsonModelList: ArrayList<Merchants>) {
        var newList = jsonModelList.groupBy {
            it.names?.fr?.firstOrNull()?.toUpperCase()
        }

        newList.forEach {
            finalList.add(MerchantsHolder(it.key.toString(), it.value as ArrayList<Merchants>))
        }

        Log.d("ResultLog","Result finalList : "+finalList.toString())

    }
}
