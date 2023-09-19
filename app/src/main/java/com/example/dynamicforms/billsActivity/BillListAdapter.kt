package com.example.dynamicforms.billsActivity

import android.content.Context
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dynamicforms.R
import com.example.dynamicforms.formActivity.data.Merchants

class BillListAdapter(
    val context: Context?,
    private var listOfHistoryResponse: List<Merchants>,
) : RecyclerView.Adapter<BillListAdapter.ViewHolder>() {

    /**
     * Find all the views of the list item
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var billTitle: TextView
        lateinit var billIcon: ImageView

        /**
         * Show the data in the views
         */

        fun bindView(item: Merchants, position: Int) {
            billTitle = itemView.findViewById(R.id.titleBill)
            billIcon = itemView.findViewById(R.id.iconBill)

            billTitle.text = item.names?.fr ?: ""

            val cleanImage: String = item.logo?.replace("data:image/png;base64,", "")
                ?.replace("data:image/jpeg;base64,", "") ?: ""

            val decodedBytes: ByteArray = Base64.decode(cleanImage, Base64.DEFAULT)
            Glide
                .with(BillsActivity.activityInstance)
                .load(decodedBytes)
                .optionalFitCenter()
                .into(billIcon)

            billIcon.scaleType = ImageView.ScaleType.CENTER_INSIDE
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
            LayoutInflater.from(context).inflate(R.layout.bill_item, parent, false)
        return ViewHolder(itemView)
    }

    fun updateData(valueList: ArrayList<Merchants>) {
        listOfHistoryResponse = valueList
        this.notifyDataSetChanged()
    }
}
