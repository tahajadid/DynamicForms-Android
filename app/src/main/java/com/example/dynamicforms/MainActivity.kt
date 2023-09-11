package com.example.dynamicforms

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.Constants.ListOfModel
import com.example.dynamicforms.util.adapters.FormAdapter
import com.example.dynamicforms.util.interfaces.JsonToFormClickListener
import com.example.dynamicforms.util.models.JSONModel
import com.example.dynamicforms.util.sigleton.DataValueHashMap
import com.example.dynamicforms.util.validate.CheckFieldValidations

class MainActivity : AppCompatActivity(), JsonToFormClickListener {

    var recyclerView: RecyclerView? = null
    var mAdapter: FormAdapter? = null
    var jsonModelList: ArrayList<JSONModel> = ArrayList<JSONModel>()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init Library HashMap
        DataValueHashMap.init()

        recyclerView = findViewById(R.id.recyclerview)
        initRecyclerView()

    }

    private fun initRecyclerView() {
        mAdapter = FormAdapter(ListOfModel, this, this)
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