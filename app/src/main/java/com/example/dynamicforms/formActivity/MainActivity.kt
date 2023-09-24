package com.example.dynamicforms.formActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dynamicforms.R
import com.example.dynamicforms.billsActivity.BillsActivity
import com.example.dynamicforms.billsActivity.data.MerchantServicesSelection
import com.example.dynamicforms.billsActivity.data.Merchants
import com.example.dynamicforms.formActivity.ui.BillServiceAdapter
import com.example.dynamicforms.formActivity.ui.BillServiceClickListener
import com.example.dynamicforms.formActivity.util.CommonUtils.getMerchantById
import com.example.dynamicforms.formActivity.util.formAdapter.FormAdapter
import com.example.dynamicforms.formActivity.util.interfaces.SubmitClickListener
import com.example.dynamicforms.formActivity.util.models.JSONModel
import com.example.dynamicforms.formActivity.util.sigleton.DataValueHashMap
import com.example.dynamicforms.formActivity.util.validate.CheckFieldValidations
import com.example.dynamicforms.merchantLogo

class MainActivity : AppCompatActivity(), SubmitClickListener, BillServiceClickListener {

    lateinit var merchants: Merchants
    var recyclerView: RecyclerView? = null
    var servicesList: RecyclerView? = null

    lateinit var imageView: ImageView

    var mAdapter: FormAdapter? = null
    var billServiceAdapter: BillServiceAdapter? = null

    var jsonModelList: ArrayList<JSONModel> = ArrayList()
    var serviceListSelection: ArrayList<MerchantServicesSelection> = ArrayList()
    var index = 0

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
        servicesList = findViewById(R.id.serviceList)
        imageView = findViewById(R.id.imageView)

        initServicesList()
        initHeader()
        activityInstance = this
    }

    private fun initServicesList() {
        if (merchants.merchantServices.size > 1) {
            servicesList!!.visibility = View.VISIBLE

            merchants.merchantServices.forEach {
                if (serviceListSelection.size == 0) {
                    serviceListSelection.add(MerchantServicesSelection(index, it, true))
                } else {
                    serviceListSelection.add(MerchantServicesSelection(index, it, false))
                }
                index += 1
            }

            Log.d("TestClick", "serviceListSelection : " + serviceListSelection.toString())

            billServiceAdapter = BillServiceAdapter(
                this,
                serviceListSelection,
                this,
            )

            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
            servicesList!!.layoutManager = layoutManager
            servicesList!!.itemAnimator = DefaultItemAnimator()
            servicesList!!.adapter = billServiceAdapter
        } else {
            servicesList!!.visibility = View.GONE
        }

        initRecyclerView(0)
    }

    override fun onResume() {
        super.onResume()
    }

    /**
     * function to set the data to the adapter of our list
     */
    private fun initRecyclerView(i: Int) {
        mAdapter = FormAdapter(
            merchants.merchantServices[i].merchantServiceFields,
            this,
        )
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = mAdapter
    }

    private fun initHeader() {
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onItemClick(position: Int, merchantServicesSelection: MerchantServicesSelection) {
        serviceListSelection.forEach {
            it.isSelected = it.MerchantServicesSelection?.code?.equals(merchantServicesSelection.MerchantServicesSelection?.code)
        }

        billServiceAdapter?.notifyDataSetChanged()

        merchantServicesSelection.id?.let { initRecyclerView(it) }
        mAdapter?.notifyDataSetChanged()
    }
}
