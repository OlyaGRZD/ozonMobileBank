package com.example.ozonmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.ozonmobile.Entyties.User
import com.example.ozonmobile.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = AppDatabase.getDb(this)



        binding.registerBtn.setOnClickListener {
            val name = binding.nameTW.text.toString().trim()
            val card = binding.cardTW.text.toString().trim()
            if (name.isNotEmpty() && card.isNotEmpty()) {
                val balance = (1000..10000).random().toDouble()
                val user = User(binding.cardTW.text.toString().toLong(),
                    binding.nameTW.text.toString(),
                    balance,
                    "user"
                )
                CoroutineScope(Dispatchers.IO).launch {
                    database.userDao().insertUser(user)
                }
                val financeFragment = FinanceFragment()

                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, financeFragment)
                    .addToBackStack(null) // Добавляем в стек возврата
                    .commit()
            }
            else {
                Toast.makeText(this, "Введите имя и номер карты", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
