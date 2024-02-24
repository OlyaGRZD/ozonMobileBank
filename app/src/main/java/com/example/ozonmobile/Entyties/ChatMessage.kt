package com.example.ozonmobile.Entyties

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "chat_history",
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["cardNumber"], childColumns = ["senderId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class ChatMessage(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val senderId: Long,
    val senderType: String, // "user" or "support"
    val message: String
)
