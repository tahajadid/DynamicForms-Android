package com.example.dynamicforms.formActivity.util.formAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.formActivity.FormConstants
import com.example.dynamicforms.formActivity.util.interfaces.JsonToFormClickListener
import com.example.dynamicforms.formActivity.util.models.JSONModel
import com.example.dynamicforms.formActivity.util.sigleton.DataValueHashMap
import com.example.dynamicforms.formActivity.util.viewholder.* // ktlint-disable no-wildcard-imports
import com.example.dynamicforms.formActivity.util.viewholder.customEditTextHolder.BindCustomEditTextHolder
import com.example.dynamicforms.formActivity.util.viewholder.customEditTextHolder.CustomEditTextHolder
import com.example.dynamicforms.formActivity.util.viewholder.listHolder.ChoiceListHolder

class FormAdapter(
    var jsonModelList: ArrayList<JSONModel>,
    var mContext: Context,
    var jsonToFormClickListener: JsonToFormClickListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        holder.setIsRecyclable(false)
        super.onViewAttachedToWindow(holder)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return if (viewType == FormConstants.TYPE_SPINNER) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_spinner, viewGroup, false)
            SpinnerViewHolder(view, jsonModelList)
        } else if (viewType == FormConstants.TYPE_DATE) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_date, viewGroup, false)
            DateViewHolder(view, jsonModelList)
        } else if (viewType == FormConstants.TYPE_SUBMIT_BUTTON) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_submit_button, viewGroup, false)
            SubmitButtonHolder(view, jsonModelList, jsonToFormClickListener)
        } else if (viewType == FormConstants.TYPE_LIST) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_bill_choice, viewGroup, false)
            ChoiceListHolder(view, jsonModelList)
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_custom_edit_text, viewGroup, false)
            CustomEditTextHolder(view, jsonModelList)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        if (position == -1) {
            return
        }
        when (holder) {
            is CustomEditTextHolder -> {
                BindCustomEditTextHolder.bindCustomEditText(jsonModelList, holder, position)
            }
            is SpinnerViewHolder -> {
                bindSpinner(holder, position)
            }
            is DateViewHolder -> {
                bindDate(holder, position)
            }
            is SubmitButtonHolder -> {
                bindSubmitButton(holder, position)
            }
        }
    }

    private fun bindSubmitButton(holder: SubmitButtonHolder, position: Int) {
        val jsonModel = jsonModelList[position]
        holder.btnSubmit.setText(jsonModel.text)
    }

    private fun bindDate(holder: DateViewHolder, position: Int) {
        val jsonModel = jsonModelList[position]
        holder.layoutDate.setHint(jsonModel.text)
        if (!DataValueHashMap.getValue(jsonModel.id).isEmpty()) {
            holder.layoutDate.editText!!.setText(DataValueHashMap.getValue(jsonModel.id))
        } else {
            holder.layoutDate.editText!!.setText(FormConstants.EMPTY_STRING)
        }
    }

    private fun bindSpinner(holder: SpinnerViewHolder, position: Int) {
        val jsonModel = jsonModelList[position]
        holder.txtSpinner.setText(jsonModel.text)
        val categoriesSpin: MutableList<String> = ArrayList()
        for (i in 0 until jsonModel.list!!.size) {
            categoriesSpin.add(jsonModel.list!!.get(i).indexText.toString())
        }
        val dataAdapterVisit: ArrayAdapter<String>
        dataAdapterVisit = ArrayAdapter(mContext, android.R.layout.simple_spinner_item, categoriesSpin)
        dataAdapterVisit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.spinner.adapter = dataAdapterVisit
        if (!DataValueHashMap.getValue(jsonModel.id).isEmpty()) {
            val spinnerPosition =
                dataAdapterVisit.getPosition(DataValueHashMap.getValue(jsonModel.id))
            holder.spinner.setSelection(spinnerPosition)
        } else {
            holder.spinner.setSelection(0)
        }
    }

    override fun getItemCount(): Int {
        return jsonModelList.size
    }

    override fun getItemViewType(position: Int): Int {
        val type: Int? = jsonModelList[position].type
        return if (type == FormConstants.TYPE_CUSTOM_EDITTEXT) {
            FormConstants.TYPE_CUSTOM_EDITTEXT
        } else if (type == FormConstants.TYPE_SPINNER) {
            FormConstants.TYPE_SPINNER
        } else if (type == FormConstants.TYPE_DATE) {
            FormConstants.TYPE_DATE
        } else if (type == FormConstants.TYPE_SUBMIT_BUTTON) {
            FormConstants.TYPE_SUBMIT_BUTTON
        } else {
            FormConstants.TYPE_LIST
        }
    }

    companion object {
        var keyValueHashMap = HashMap<String, String>()
    }
}
