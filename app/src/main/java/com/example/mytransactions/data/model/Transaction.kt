package com.example.mytransactions.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey
    val id: Int,
    val transactionDate: String,
    val summary: String,
    val debit: Double,
    val credit: Double,
)