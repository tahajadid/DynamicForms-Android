package com.example.dynamicforms.util.formAdapter

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.util.FormConstants
import com.example.dynamicforms.util.interfaces.JsonToFormClickListener
import com.example.dynamicforms.util.models.JSONModel
import com.example.dynamicforms.util.sigleton.DataValueHashMap
import com.example.dynamicforms.util.viewholder.*
import com.example.dynamicforms.util.viewholder.checkBoxHolder.BindCheckBox
import com.example.dynamicforms.util.viewholder.checkBoxHolder.CheckboxViewHolder
import com.example.dynamicforms.util.viewholder.editTextHolder.BindEditText
import com.example.dynamicforms.util.viewholder.editTextHolder.EditTextViewHolder
import com.example.dynamicforms.util.viewholder.listHolder.ChoiceListHolder

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
        return if (viewType == FormConstants.TYPE_TEXT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_textview, viewGroup, false)
            TextViewHolder(view, jsonModelList)
        } else if (viewType == FormConstants.TYPE_EDITTEXT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_edittext, viewGroup, false)
            EditTextViewHolder(view, jsonModelList)
        } else if (viewType == FormConstants.TYPE_SPINNER) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_spinner, viewGroup, false)
            SpinnerViewHolder(view, jsonModelList)
        } else if (viewType == FormConstants.TYPE_RADIO) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_radio, viewGroup, false)
            RadioViewHolder(view, jsonModelList)
        } else if (viewType == FormConstants.TYPE_SPACE) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_space, viewGroup, false)
            SpaceViewHolder(view, jsonModelList)
        } else if (viewType == FormConstants.TYPE_DATE) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_date, viewGroup, false)
            DateViewHolder(view, jsonModelList)
        } else if (viewType == FormConstants.TYPE_SUBMIT_BUTTON) {
            view =
                LayoutInflater.from(mContext).inflate(R.layout.item_submit_button, viewGroup, false)
            SubmitButtonHolder(view, jsonModelList, jsonToFormClickListener)
        } else if (viewType == FormConstants.TYPE_LIST) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_bill_choice, viewGroup, false)
            ChoiceListHolder(view, jsonModelList)
        } else if (viewType == FormConstants.TYPE_CHECKBOX) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_checkbox, viewGroup, false)
            CheckboxViewHolder(view, jsonModelList)
        } else if (viewType == FormConstants.TYPE_ADD_AGAIN_BUTTON) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_add_again, viewGroup, false)
            AddAgainButtonHolder(view, jsonModelList, jsonToFormClickListener)
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_space, viewGroup, false)
            SpaceViewHolder(view, jsonModelList)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        if (position == -1) {
            return
        }
        if (holder is TextViewHolder) {
            bindTextView(holder, position)
        } else if (holder is EditTextViewHolder) {
            BindEditText.bindEditText(jsonModelList, holder, position)
        } else if (holder is SpinnerViewHolder) {
            bindSpinner(holder, position)
        } else if (holder is RadioViewHolder) {
            bindRadio(holder, position)
        } else if (holder is SpaceViewHolder) {
            bindSpace(holder, position)
        } else if (holder is DateViewHolder) {
            bindDate(holder, position)
        } else if (holder is SubmitButtonHolder) {
            bindSubmitButton(holder, position)
        } else if (holder is CheckboxViewHolder) {
            BindCheckBox.bindCheckBox(jsonModelList, holder, position)
        } else if (holder is AddAgainButtonHolder) {
            btnAddAgainButton(holder, position)
        }
    }

    private fun btnAddAgainButton(holder: AddAgainButtonHolder, position: Int) {
        val jsonModel = jsonModelList[position]
        holder.btnAddAgain.setText(jsonModel.text)
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

    private fun bindSpace(holder: SpaceViewHolder, position: Int) {
        val jsonModel = jsonModelList[position]
    }

    private fun bindRadio(holder: RadioViewHolder, position: Int) {
        val jsonModel = jsonModelList[position]
        holder.txtRadio.setText(jsonModel.text)
        holder.rGroup.removeAllViews()
        for (i in 0 until jsonModel.list!!.size) {
            val radioButton = RadioButton(mContext)
            radioButton.setText(jsonModel.list!!.get(i).indexText)
            radioButton.id = jsonModel.list!!.get(i).index!!
            radioButton.setButtonDrawable(R.drawable.toggle_states)
            radioButton.setPadding(16, 24, 8, 8)
            radioButton.setTextColor(mContext.resources.getColor(R.color.black))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                radioButton.backgroundTintList =
                    ColorStateList.valueOf(mContext.resources.getColor(R.color.black))
            }
            val params = RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT,
            )
            params.setMargins(16, 24, 8, 8)
            holder.rGroup.addView(radioButton, params)
        }
        if (position != -1 && DataValueHashMap.getValue(jsonModelList[position].id)
                .equals(FormConstants.EMPTY_STRING, ignoreCase = true)
        ) {
            DataValueHashMap.put(
                jsonModelList[position].id.toString(),
                if (holder.rGroup.checkedRadioButtonId == -1) {
                    FormConstants.EMPTY_STRING
                } else {
                    (
                        holder.itemView.findViewById<View>(
                            holder.rGroup.checkedRadioButtonId,
                        ) as RadioButton
                        ).text.toString()
                },
            )
        }
        outer@ for (i in 0 until holder.rGroup.childCount) {
            val id = holder.rGroup.getChildAt(i).id
            val radioButton = holder.rGroup.findViewById<RadioButton>(id)
            if (!DataValueHashMap.getValue(jsonModel.id).isEmpty()) {
                if (radioButton.text.toString()
                        .equals(DataValueHashMap.getValue(jsonModel.id), ignoreCase = true)
                ) {
                    holder.rGroup.clearCheck()
                    radioButton.isChecked = true
                    break@outer
                }
            }
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

    private fun bindTextView(holder: TextViewHolder, position: Int) {
        val jsonModel = jsonModelList[position]
        holder.txtHead.setText(jsonModel.text)
    }

    override fun getItemCount(): Int {
        return jsonModelList.size
    }

    override fun getItemViewType(position: Int): Int {
        val type: Int? = jsonModelList[position].type
        return if (type == FormConstants.TYPE_TEXT) {
            FormConstants.TYPE_TEXT
        } else if (type == FormConstants.TYPE_EDITTEXT) {
            FormConstants.TYPE_EDITTEXT
        } else if (type == FormConstants.TYPE_SPINNER) {
            FormConstants.TYPE_SPINNER
        } else if (type == FormConstants.TYPE_RADIO) {
            FormConstants.TYPE_RADIO
        } else if (type == FormConstants.TYPE_SPACE) {
            FormConstants.TYPE_SPACE
        } else if (type == FormConstants.TYPE_DATE) {
            FormConstants.TYPE_DATE
        } else if (type == FormConstants.TYPE_SUBMIT_BUTTON) {
            FormConstants.TYPE_SUBMIT_BUTTON
        } else if (type == FormConstants.TYPE_LIST) {
            FormConstants.TYPE_LIST
        } else if (type == FormConstants.TYPE_CHECKBOX) {
            FormConstants.TYPE_CHECKBOX
        } else if (type == FormConstants.TYPE_ADD_AGAIN_BUTTON) {
            FormConstants.TYPE_ADD_AGAIN_BUTTON
        } else {
            FormConstants.TYPE_SPACE
        }
    }

    companion object {
        var keyValueHashMap = HashMap<String, String>()
    }
}
