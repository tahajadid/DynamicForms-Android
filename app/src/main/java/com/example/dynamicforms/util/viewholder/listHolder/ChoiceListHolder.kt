package com.example.dynamicforms.util.viewholder.listHolder

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.MainActivity
import com.example.dynamicforms.R
import com.example.dynamicforms.data.Choice
import com.example.dynamicforms.util.models.JSONModel

class ChoiceListHolder(itemView: View, jsonModelList: List<JSONModel>) :
    RecyclerView.ViewHolder(itemView), ChoiceClickListener {
    var listOfChoice: RecyclerView
    var choiceItemAdapter: ChoiceItemAdapter

    var listOChoice = mutableListOf<Choice>(
        Choice("0", "0", false),
        Choice("1", "1", false),
        Choice("2", "2", false),
        Choice("3", "3", false),
        Choice("4", "4", false),
        Choice("5", "5", false),
        Choice("6", "6", false),
    )
    init {
        listOfChoice = itemView.findViewById(R.id.list_of_choices)

        // Init adapter list
        choiceItemAdapter = ChoiceItemAdapter(
            MainActivity.activityInstance.applicationContext,
            listOChoice as ArrayList<Choice>,
            this,
        )

        // we set a GridLayout to have the same margin and size
        // of the favourite items like the other item
        listOfChoice.apply {
            layoutManager = GridLayoutManager(this.context, 2)
            adapter = choiceItemAdapter
        }
    }

    override fun onItemClick(position: Int, choice: Choice) {
        listOChoice.forEach {
            it.isSelected = it.id?.equals(position.toString())
        }
        choiceItemAdapter.notifyDataSetChanged()
    }
}
