package com.example.dynamicforms.formActivity.ui

import com.example.dynamicforms.billsActivity.data.MerchantServicesSelection

interface BillServiceClickListener {
    fun onItemClick(position: Int, merchantServicesSelection: MerchantServicesSelection)
}
