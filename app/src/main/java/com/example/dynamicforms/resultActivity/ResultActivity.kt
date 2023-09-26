package com.example.dynamicforms.resultActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dynamicforms.R

class ResultActivity : AppCompatActivity() {
    lateinit var results: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        results = findViewById(R.id.results)

        initComponents()
    }

    /**
     * function to init the content view
     */
    private fun initComponents() {
    }
}
