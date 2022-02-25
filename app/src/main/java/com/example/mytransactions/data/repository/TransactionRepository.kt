package com.example.mytransactions.data.repository

import com.example.mytransactions.data.model.Transaction
import com.example.mytransactions.data.local.TransactionDao
import com.example.mytransactions.data.remote.TransactionRemoteDataSource
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val remoteDataSource: TransactionRemoteDataSource,
    private val localDataSource: TransactionDao
) {
    fun getLocalTransaction(id: Int) = localDataSource.getTransaction(id)

    suspend fun saveTransaction(transaction: Transaction) = localDataSource.insert(transaction)

    fun getLocalTransactions() = localDataSource.getAllTransactions()

    suspend fun getRemoteTransactions() = remoteDataSource.getAllTransactions()

    suspend fun saveTransactions(transactions: List<Transaction>) = localDataSource.insertTransactions(transactions)
}