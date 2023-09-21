package com.example.dynamicforms.formActivity.util.formAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.billsActivity.data.MerchantServiceFields
import com.example.dynamicforms.formActivity.FormConstants
import com.example.dynamicforms.formActivity.util.sigleton.DataValueHashMap
import com.example.dynamicforms.formActivity.util.viewholder.* // ktlint-disable no-wildcard-imports
import com.example.dynamicforms.formActivity.util.viewholder.customEditTextHolder.BindCustomEditTextHolder
import com.example.dynamicforms.formActivity.util.viewholder.customEditTextHolder.CustomEditTextHolder
import com.example.dynamicforms.formActivity.util.viewholder.listHolder.ChoiceListHolder

class FormAdapter(
    var fieldList: ArrayList<MerchantServiceFields>,
    var mContext: Context,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        holder.setIsRecyclable(false)
        super.onViewAttachedToWindow(holder)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return when (viewType) {
            FormConstants.TYPE_SPINNER -> {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_spinner, viewGroup, false)
                SpinnerViewHolder(view, fieldList)
            }
            FormConstants.TYPE_LIST -> {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_bill_choice, viewGroup, false)
                ChoiceListHolder(view, fieldList)
            }
            else -> {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_custom_edit_text, viewGroup, false)
                CustomEditTextHolder(view, fieldList)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        if (position == -1) {
            return
        }
        when (holder) {
            is CustomEditTextHolder -> {
                BindCustomEditTextHolder.bindCustomEditText(fieldList, holder, position)
            }
            is SpinnerViewHolder -> {
                bindSpinner(holder, position)
            }
        }
    }

    private fun bindSpinner(holder: SpinnerViewHolder, position: Int) {
        val jsonModel = fieldList[position]
        holder.txtSpinner.setText(jsonModel.names?.fr ?: "")
        val categoriesSpin: MutableList<String> = ArrayList()
        for (i in 0 until jsonModel.merchantServiceFieldListOfValues!!.length) {
            categoriesSpin.add(jsonModel.merchantServiceFieldListOfValues!!.get(i).toString())
        }
        val dataAdapterVisit: ArrayAdapter<String>
        dataAdapterVisit = ArrayAdapter(mContext, android.R.layout.simple_spinner_item, categoriesSpin)
        dataAdapterVisit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.spinner.adapter = dataAdapterVisit
        if (!DataValueHashMap.getValue(jsonModel.code).isEmpty()) {
            val spinnerPosition =
                dataAdapterVisit.getPosition(DataValueHashMap.getValue(jsonModel.code))
            holder.spinner.setSelection(spinnerPosition)
        } else {
            holder.spinner.setSelection(0)
        }
    }

    override fun getItemCount(): Int {
        return fieldList.size
    }

    /**
     * Return the view type of the item at position for the purposes of view recycling.
     */
    override fun getItemViewType(position: Int): Int {
        val type: String? = fieldList[position].dataType
        val newType = getMatchedInt(type.toString())
        return if (newType == FormConstants.TYPE_CUSTOM_EDITTEXT) {
            FormConstants.TYPE_CUSTOM_EDITTEXT
        } else if (newType == FormConstants.TYPE_SPINNER) {
            FormConstants.TYPE_SPINNER
        } else {
            FormConstants.TYPE_LIST
        }
    }

    fun getMatchedInt(type: String): Int {
        when (type) {
            "A" -> return 2
            "S" -> return 3
            "N" -> return 2
            "G" -> return 2
            "M" -> return 2
            else -> return 11
        }
    }
}
