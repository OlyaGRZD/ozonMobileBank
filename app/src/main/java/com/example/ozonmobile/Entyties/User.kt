package com.example.ozonmobile.Entyties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = false)
    val cardNumber: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "balance")
    var balance: Double = 0.0,
    @ColumnInfo(name = "role")
    val role: String // "user" or "support"
)
