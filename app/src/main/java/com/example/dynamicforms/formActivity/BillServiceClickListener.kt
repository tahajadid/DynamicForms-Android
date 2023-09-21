package com.example.dynamicforms.formActivity

import com.example.dynamicforms.billsActivity.data.MerchantServicesSelection

interface BillServiceClickListener {
    fun onItemClick(position: Int, merchantServicesSelection: MerchantServicesSelection)
}
