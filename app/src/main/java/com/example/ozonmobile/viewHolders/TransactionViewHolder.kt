package com.example.ozonmobile.viewHolders

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ozonmobile.R
import com.example.ozonmobile.dataClasses.Transaction

class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(transaction: Transaction) {
        // Наполни данными элементы макета transaction_item
        itemView.findViewById<TextView>(R.id.transactionOrganisation).text = transaction.organization
        itemView.findViewById<TextView>(R.id.transactionType).text = transaction.type
        itemView.findViewById<TextView>(R.id.transactionSign).text = transaction.sign
        itemView.findViewById<TextView>(R.id.transactionAmount).text = transaction.amount

        if ("+"==transaction.sign){
            val textsColor = ContextCompat.getColor(itemView.context, R.color.green)
            itemView.findViewById<TextView>(R.id.transactionAmount).setTextColor(textsColor)
            itemView.findViewById<TextView>(R.id.transactionSign).setTextColor(textsColor)
        }
        else{
            val textsColor = ContextCompat.getColor(itemView.context, R.color.red)
            itemView.findViewById<TextView>(R.id.transactionAmount).setTextColor(textsColor)
            itemView.findViewById<TextView>(R.id.transactionSign).setTextColor(textsColor)
        }
    }
}
