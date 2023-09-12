package com.example.dynamicforms.util.viewholder.listHolder

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.data.Choice

class ChoiceItemAdapter(
    private val context: Context?,
    private var listOfRecharge: ArrayList<Choice>,
    private val choiceClickListener: ChoiceClickListener,
) : RecyclerView.Adapter<ChoiceItemAdapter.ViewHolder>() {

    /**
     * Find all the views of the list item
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var amount: TextView
        lateinit var amountContainer: ConstraintLayout

        /**
         * Show the data in the views
         */
        fun bindView(item: Choice, position: Int, choiceClickListener: ChoiceClickListener) {
            amount = itemView.findViewById(R.id.amount_value)
            amountContainer = itemView.findViewById(R.id.amount_container)

            amount.text = item.id

            // item selected
            if (item.isSelected == true) {
                amount.setTextColor(Color.BLACK)
            } else {
                amount.setTextColor(Color.GREEN)
            }

            itemView.setOnClickListener {
                choiceClickListener.onItemClick(position, item)
            }
        }
    }

    override fun getItemCount(): Int = listOfRecharge.size

    /**
     * Inside this method data will be displayed at the specified position
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listOfRecharge[position]
        holder.bindView(item, position, choiceClickListener)
    }

    /**
     * Inside this method we specify the layout that each item of the RecyclerView should use
     * onCreateViewHolder has return type of RecyclerView.ViewHolder which represent each row of recyclerView.
     * Using Inflater get the view of above defined stars_recharge_amount_item and pass it to viewHolder
     * constructor and then return.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.one_selection_content, parent, false)
        return ViewHolder(itemView)
    }
}
