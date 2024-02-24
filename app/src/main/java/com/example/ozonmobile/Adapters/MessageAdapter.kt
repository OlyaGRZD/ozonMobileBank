package com.example.ozonmobile.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ozonmobile.Entyties.ChatMessage
import com.example.ozonmobile.R
import kotlinx.coroutines.flow.Flow


class MessageAdapter(private val chatMessages: List<ChatMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val USER_MESSAGE = 1
        private const val SUPPORT_MESSAGE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            USER_MESSAGE -> {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.message_user, parent, false)
                UserMessageViewHolder(view)
            }
            SUPPORT_MESSAGE -> {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.message_support, parent, false)
                SupportMessageViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chatMessage = chatMessages[position]
        when (holder.itemViewType) {
            USER_MESSAGE -> {
                val userHolder = holder as UserMessageViewHolder
                userHolder.bind(chatMessage)
            }
            SUPPORT_MESSAGE -> {
                val supportHolder = holder as SupportMessageViewHolder
                supportHolder.bind(chatMessage)
            }
        }
    }

    override fun getItemCount(): Int = chatMessages.size

    override fun getItemViewType(position: Int): Int {
        return if (chatMessages[position].senderType == "user") USER_MESSAGE else SUPPORT_MESSAGE
    }

    inner class UserMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userNameTextView: TextView = itemView.findViewById(R.id.userName)
        private val messageTextView: TextView = itemView.findViewById(R.id.message)

        fun bind(chatMessage: ChatMessage) {
            userNameTextView.text = chatMessage.senderType // Логика для определения имени отправителя
            messageTextView.text = chatMessage.message
        }
    }

    inner class SupportMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val supportNameTextView: TextView = itemView.findViewById(R.id.supportName)
        private val messageTextView: TextView = itemView.findViewById(R.id.message)

        fun bind(chatMessage: ChatMessage) {
            supportNameTextView.text = "Поддержка" // Фиксированное значение для сообщений от поддержки
            messageTextView.text = chatMessage.message
        }
    }
}


