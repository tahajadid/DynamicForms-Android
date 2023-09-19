package com.example.dynamicforms.billsActivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicforms.R
import com.example.dynamicforms.formActivity.data.MerchantsHolder

class BillSectionAdapter(
    val context: Context?,
    private var listOfHistoryResponse: List<MerchantsHolder>,
) : RecyclerView.Adapter<BillSectionAdapter.ViewHolder>() {

    /**
     * Find all the views of the list item
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var leter: TextView
        lateinit var listOfBills: RecyclerView
        lateinit var billListAdapter: BillListAdapter

        /**
         * Show the data in the views
         */

        fun bindView(item: MerchantsHolder, position: Int) {
            leter = itemView.findViewById(R.id.letter)
            listOfBills = itemView.findViewById(R.id.list_bills)

            leter.text = item.title

            billListAdapter = BillListAdapter(listOfBills.context, item.merchants)
            listOfBills.apply {
                layoutManager = GridLayoutManager(this.context, 3)
                adapter = billListAdapter
            }
        }
    }

    override fun getItemCount(): Int = listOfHistoryResponse.size

    /**
     * Inside this method data will be displayed at the specified position
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listOfHistoryResponse[position]
        holder.bindView(item, position)
    }

    /**
     * Inside this method onCreateViewHolder
     */

    // Change layout Item with the new one
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View =
            LayoutInflater.from(context).inflate(R.layout.bill_section_layout, parent, false)
        return ViewHolder(itemView)
    }

    fun updateData(valueList: ArrayList<MerchantsHolder>) {
        listOfHistoryResponse = valueList
        this.notifyDataSetChanged()
    }
}
