package com.example.ozonmobile.Entyties

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "user_categories",
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["cardNumber"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Category::class, parentColumns = ["id"], childColumns = ["categoryId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class UserCategory(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val userId: Long,
    val categoryId: Long
)

