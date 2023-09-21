package com.example.dynamicforms.formActivity

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.billsActivity.data.MerchantServicesSelection


class BillServiceAdapter(
    private val context: Context?,
    private var listOfRecharge: ArrayList<MerchantServicesSelection>,
    private val billServiceClickListener: BillServiceClickListener,
) : RecyclerView.Adapter<BillServiceAdapter.ViewHolder>() {

    /**
     * Find all the views of the list item
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var selectionText: TextView
        lateinit var selectionIcon: ImageView

        lateinit var amountContainer: ConstraintLayout

        /**
         * Show the data in the views
         */
        @SuppressLint("ResourceAsColor")
        fun bindView(item: MerchantServicesSelection, position: Int, billServiceClickListener: BillServiceClickListener) {
            selectionText = itemView.findViewById(R.id.selection_text)
            selectionIcon = itemView.findViewById(R.id.selection_icon)
            amountContainer = itemView.findViewById(R.id.amount_container)

            selectionText.text = item.MerchantServicesSelection?.names?.dflt.toString()

            // item selected
            if (item.isSelected == true) {
                selectionText.setTextColor(
                    ContextCompat.getColor(MainActivity.activityInstance.applicationContext,R.color.mag_color))
                selectionIcon.setImageResource(R.drawable.active_radio_button_icon)
            } else {
                selectionText.setTextColor(Color.BLACK)
                selectionIcon.setImageResource(R.drawable.inactive_radio_button_icon)
            }

            itemView.setOnClickListener {
                billServiceClickListener.onItemClick(position, item)
            }
        }
    }

    override fun getItemCount(): Int = listOfRecharge.size

    /**
     * Inside this method data will be displayed at the specified position
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listOfRecharge[position]
        holder.bindView(item, position, billServiceClickListener)
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