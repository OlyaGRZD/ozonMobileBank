package com.example.ozonmobile.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ozonmobile.R
import com.example.ozonmobile.dataClasses.CashbackCategory

class CashbackCategoryAdapter(private val categories: List<CashbackCategory>) :
    RecyclerView.Adapter<CashbackCategoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryNameTextView: TextView = itemView.findViewById(R.id.categoryNameTW)
        val categoryCheckBox: CheckBox = itemView.findViewById(R.id.categoryCheckBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cashback_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryNameTextView.text = category.name
        holder.categoryCheckBox.isChecked = category.isSelected

        holder.categoryCheckBox.setOnCheckedChangeListener { _, isChecked ->
            category.isSelected = isChecked
        }
    }

    override fun getItemCount(): Int = categories.size
    fun getSelectedCategories(): List<CashbackCategory> {
        val selectedCategories = mutableListOf<CashbackCategory>()
        for (category in categories ) {
            if (category.isSelected) {
                selectedCategories.add(category)
            }
        }
        return selectedCategories
    }
}
