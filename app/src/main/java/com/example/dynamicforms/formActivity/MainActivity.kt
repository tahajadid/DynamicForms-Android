package com.example.dynamicforms.formActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Base64
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dynamicforms.R
import com.example.dynamicforms.billsActivity.BillsActivity
import com.example.dynamicforms.billsActivity.data.Merchants
import com.example.dynamicforms.formActivity.util.CommonUtils
import com.example.dynamicforms.formActivity.util.CommonUtils.getMerchantById
import com.example.dynamicforms.formActivity.util.formAdapter.FormAdapter
import com.example.dynamicforms.formActivity.util.interfaces.JsonToFormClickListener
import com.example.dynamicforms.formActivity.util.models.JSONModel
import com.example.dynamicforms.formActivity.util.sigleton.DataValueHashMap
import com.example.dynamicforms.formActivity.util.validate.CheckFieldValidations
import com.example.dynamicforms.merchantLogo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(), JsonToFormClickListener {

    lateinit var merchants: Merchants
    var recyclerView: RecyclerView? = null
    lateinit var imageView: ImageView

    var mAdapter: FormAdapter? = null
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

        val intent = getIntent()
        val idMerchant = intent.getIntExtra("idMerchant", 0)
        merchants = getMerchantById(idMerchant)

        recyclerView = findViewById(R.id.recyclerview)
        imageView = findViewById(R.id.imageView)

        initRecyclerView()
        fetchData()
        activityInstance = this
    }

    override fun onResume() {
        super.onResume()
    }

    /**
     * function to read data from json file
     */
    private fun fetchData() {
        val json: String = CommonUtils.loadJSONFromAsset(applicationContext, DATA_JSON_PATH).toString()
        var jsonModelList1: ArrayList<JSONModel> = Gson().fromJson(json, object : TypeToken<List<JSONModel?>?>() {}.type)
        jsonModelList.addAll(jsonModelList1)
        mAdapter!!.notifyDataSetChanged()
    }

    /**
     * function to set the data to the adapter of our list
     */
    private fun initRecyclerView() {
        mAdapter = FormAdapter(
            merchants.merchantServices[1].merchantServiceFields,
            this,
        )
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = mAdapter

        val cleanImage: String = merchantLogo.replace("data:image/png;base64,", "")
            ?.replace("data:image/jpeg;base64,", "") ?: ""

        val decodedBytes: ByteArray = Base64.decode(cleanImage, Base64.DEFAULT)
        Glide
            .with(BillsActivity.activityInstance)
            .load(decodedBytes)
            .optionalFitCenter()
            .into(imageView)
    }

    override fun onSubmitButtonClick() {
        if (!CheckFieldValidations.isFieldsValidated(recyclerView!!, jsonModelList)) {
            Toast.makeText(this, "Validation Failed", Toast.LENGTH_SHORT).show()
            return
        }
    }
}
