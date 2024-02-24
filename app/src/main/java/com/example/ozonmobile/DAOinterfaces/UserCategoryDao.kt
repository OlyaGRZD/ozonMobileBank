package com.example.ozonmobile.DAOinterfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ozonmobile.Entyties.UserCategory

@Dao
interface UserCategoryDao {
    @Query("SELECT * FROM user_categories")
    fun getAllUserCategories(): List<UserCategory>

    @Query("SELECT * FROM user_categories WHERE id = :id")
    fun getUserCategoryById(id: Long): UserCategory?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserCategory(userCategory: UserCategory)

    @Delete
    suspend fun deleteUserCategory(userCategory: UserCategory)
}
