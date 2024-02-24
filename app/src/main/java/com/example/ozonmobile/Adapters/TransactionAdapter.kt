package com.example.ozonmobile.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ozonmobile.R
import com.example.ozonmobile.dataClasses.DateItem
import com.example.ozonmobile.dataClasses.Transaction
import com.example.ozonmobile.dataClasses.TransactionItem
import com.example.ozonmobile.viewHolders.DateViewHolder
import com.example.ozonmobile.viewHolders.TransactionViewHolder

class TransactionAdapter(private val items: List<TransactionItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> DateViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.date_item, parent, false))
            2 -> TransactionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false))
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DateViewHolder -> holder.bind(items[position] as DateItem)
            is TransactionViewHolder -> holder.bind(items[position] as Transaction)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].viewType

}
