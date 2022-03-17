package com.example.exam6.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")

data class CardDB (
    @PrimaryKey
    val id: String,

    val cardNumber: String,
    val cardName: String,
    val expireData: String,
    val cvv: Long,
    val isActive: Boolean,
)