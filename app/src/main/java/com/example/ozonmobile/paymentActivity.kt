package com.example.ozonmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.ozonmobile.Entyties.Transaction
import com.example.ozonmobile.Entyties.User
import com.example.ozonmobile.databinding.ActivityPaymentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class paymentActivity : AppCompatActivity() {

    lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editTextPhone: EditText = findViewById(R.id.editTextPhone)
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            val phoneNumber = editTextPhone.text.toString()
            if (phoneNumber.isNotEmpty() && phoneNumber.length>=11) {
                // Показать поле для ввода суммы и кнопку отправки
                binding.cardViewAmount.visibility = View.VISIBLE
            } else {
                // Показать сообщение об ошибке, если номер телефона не введен
                Toast.makeText(this, "Введите номер телефона", Toast.LENGTH_SHORT).show()
            }
        }

        binding.sendButton.setOnClickListener {
            if(binding.editTextAmount.text.isNotEmpty()){
                val intent = Intent(this, SuccessActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
//4919