package com.example.ozonmobile.dataClasses

import com.example.ozonmobile.dataClasses.TransactionItem

data class Transaction(val organization: String,
                       val type: String,
                       val sign: String,
                       val amount: String) : TransactionItem(2)
