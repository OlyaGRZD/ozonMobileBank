package com.example.ozonmobile.dataClasses

sealed class TransactionItem(val viewType: Int) {
    data class DateItem(val date: String) : TransactionItem(1)
    data class Transaction(val organization: String, val type: String, val sign: String, val amount: String) : TransactionItem(2)

}