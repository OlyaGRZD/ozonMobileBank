package com.example.ozonmobile
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ozonmobile.Adapters.CashbackCategoryAdapter
import com.example.ozonmobile.dataClasses.CashbackCategory
import com.example.ozonmobile.databinding.ActivityBonusBinding

class BonusActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryAdapter: CashbackCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bonus)

        val cashbackCategories = listOf(
            CashbackCategory("1% Еда", false),
            CashbackCategory("5% Техника", false),
            CashbackCategory("3% Путешествия", false),
            CashbackCategory("1% Еда", false),
            CashbackCategory("5% Техника", false),
        )

        recyclerView = findViewById(R.id.recyclerView)
        categoryAdapter = CashbackCategoryAdapter(cashbackCategories)
        recyclerView.adapter = categoryAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            val selectedCategories = categoryAdapter.getSelectedCategories()

            saveSelectedCategories(selectedCategories)
            Toast.makeText(this, "Категории кэшбека сохранены", Toast.LENGTH_SHORT).show()

            // Вызов метода, который сохраняет выбранные категории и делает их недоступными для изменений
        }
    }

    private fun saveSelectedCategories(selectedCategories: List<CashbackCategory>) {
        val sharedPreferences = getSharedPreferences("selected_categories", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Очищаем предыдущие значения
        editor.clear()

        // Сохраняем выбранные категории в виде строки через разделитель
        val selectedCategoriesString = selectedCategories.joinToString(",") { it.name }
        editor.putString("selected_categories", selectedCategoriesString)

        editor.apply()
    }
}



