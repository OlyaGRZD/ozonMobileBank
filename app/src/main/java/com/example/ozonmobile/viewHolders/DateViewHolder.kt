package com.example.ozonmobile.viewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ozonmobile.R
import com.example.ozonmobile.dataClasses.DateItem

class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(dateItem: DateItem) {
        // Наполни данными элементы макета date_item
        itemView.findViewById<TextView>(R.id.dateTextView).text = dateItem.date
    }
}
