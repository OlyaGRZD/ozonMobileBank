package com.example.ozonmobile

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ozonmobile.DAOinterfaces.CategoryDao
import com.example.ozonmobile.DAOinterfaces.ChatMessageDao
import com.example.ozonmobile.DAOinterfaces.TransactionDao
import com.example.ozonmobile.DAOinterfaces.UserCategoryDao
import com.example.ozonmobile.DAOinterfaces.UserDao
import com.example.ozonmobile.Entyties.Category
import com.example.ozonmobile.Entyties.ChatMessage
import com.example.ozonmobile.Entyties.Transaction
import com.example.ozonmobile.Entyties.User
import com.example.ozonmobile.Entyties.UserCategory

@Database(entities = [User::class, Transaction::class, Category::class, UserCategory::class, ChatMessage::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
    abstract fun UserCategoryDao(): UserCategoryDao
    abstract fun chatMessageDao(): ChatMessageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDb(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration() // Указываем использовать миграцию при изменении схемы
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
