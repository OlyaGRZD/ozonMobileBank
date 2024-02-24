package com.example.ozonmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ozonmobile.Adapters.MessageAdapter
import com.example.ozonmobile.Entyties.ChatMessage
import com.example.ozonmobile.databinding.ActivityChatBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ChatActivity : AppCompatActivity() {

    lateinit var binding: ActivityChatBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var messageAdapter: MessageAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chatMessageDao = AppDatabase.getDb(applicationContext).chatMessageDao()
        val messages = mutableListOf<ChatMessage>()
        messageAdapter = MessageAdapter(messages)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = messageAdapter



        CoroutineScope(Dispatchers.IO).launch {
            val userDao = AppDatabase.getDb(applicationContext).userDao()
            val lastUser = userDao.getLastUser()
            val lastUserId = lastUser?.cardNumber ?: -1
            if (lastUser != null) {
                val senderId = lastUser.cardNumber

                binding.sendButton.setOnClickListener {
                    val messageText = binding.messageEditText.text.toString().trim()
                    if (messageText.isNotEmpty()) {
                        val message = ChatMessage(
                            null,
                            lastUserId,
                            "user",
                            messageText
                        )
                        val supportMessage = ChatMessage(
                            null,
                            senderId,
                            "support",
                            "Ваш запрос уже находится в обработке. Спасибо за обращение!")
                        // Запуск операции в фоновом режиме
                        CoroutineScope(Dispatchers.IO).launch {
                            chatMessageDao.insertChatMessage(message)
                            chatMessageDao.insertChatMessage(supportMessage)
                        }

                    }
                    binding.messageEditText.text.clear()
                }

                // Получение сообщений из базы данных в фоновом режиме
                CoroutineScope(Dispatchers.IO).launch {
                    val allMessagesFlow = chatMessageDao.getUserMessages(lastUserId)
                    allMessagesFlow.collect{allMessages ->
                        withContext(Dispatchers.Main) {
                            recyclerView.apply {
                                layoutManager = LinearLayoutManager(this@ChatActivity)
                                adapter = MessageAdapter(allMessages)
                            }
                        }
                    }
                }
            }
        }
    }
}

