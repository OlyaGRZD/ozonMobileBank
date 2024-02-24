package com.example.ozonmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ozonmobile.Adapters.TransactionAdapter
import com.example.ozonmobile.dataClasses.DateItem
import com.example.ozonmobile.dataClasses.Transaction
import com.example.ozonmobile.dataClasses.TransactionItem

class TransactionActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        // Получи данные для отображения
        val transactionItems = getSampleTransactionItems()

        // Настрой RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        transactionAdapter = TransactionAdapter(transactionItems)
        recyclerView.adapter = transactionAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getSampleTransactionItems(): List<TransactionItem> {
        val items = mutableListOf<TransactionItem>()

        // Добавь тестовые данные, например:
        items.add(DateItem("5 февраля"))
        items.add(Transaction("ООО Рога и Копыта", "Покупка", "+","1000 руб"))
        items.add(Transaction("ИП Иванов", "Продажа", "-","500 руб"))
        items.add(DateItem("2 февраля"))
        items.add(Transaction("ООО Пример", "Покупка", "+","800 руб"))
        // ...

        return items
    }
}
