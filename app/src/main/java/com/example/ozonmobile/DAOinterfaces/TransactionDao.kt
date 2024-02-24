package com.example.ozonmobile.DAOinterfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ozonmobile.Entyties.Transaction

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): List<Transaction>

    @Query("SELECT * FROM transactions WHERE id = :id")
    fun getTransactionById(id: Long): Transaction?

    @Query("SELECT * FROM transactions WHERE userId = :userId")
    fun getTransactionsByUserId(userId: Long): List<Transaction>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transaction: Transaction)

    @Delete
    fun deleteTransaction(transaction: Transaction)
}
