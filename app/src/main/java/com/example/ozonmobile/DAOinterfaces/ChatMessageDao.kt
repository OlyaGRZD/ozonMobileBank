package com.example.ozonmobile.DAOinterfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ozonmobile.Entyties.ChatMessage
import com.example.ozonmobile.Entyties.User
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatMessageDao {
    @Query("SELECT * FROM chat_history")
    fun getAllChatMessages(): Flow<List<ChatMessage>>

    @Query("SELECT * FROM chat_history WHERE id = :id")
    fun getChatMessageById(id: Long): ChatMessage?

    @Query("SELECT * FROM chat_history WHERE senderId = :userId")
    fun getUserMessages(userId: Long): Flow<List<ChatMessage>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertChatMessage(chatMessage: ChatMessage)

    @Delete
    fun deleteChatMessage(chatMessage: ChatMessage)
}
