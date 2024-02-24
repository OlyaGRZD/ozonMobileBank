package com.example.ozonmobile.Entyties

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "transactions",
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["cardNumber"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0L,
    val userId: Long,
    val amount: Double,
    val transType: String,
    val organization: String,
    val sign: String
)
