package com.example.ozonmobile.DAOinterfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ozonmobile.Entyties.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE cardNumber = :id")
    fun getUserById(id: Long): User?

    @Query("SELECT * FROM users ORDER BY cardNumber DESC LIMIT 1")
    fun getLastUser(): User?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertUser(user: User): Long // Возвращаем Long

    @Delete
     fun deleteUser(user: User): Int // Возвращаем Long
}