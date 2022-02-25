package com.example.mytransactions.data.remote

import javax.inject.Inject

class TransactionRemoteDataSource @Inject constructor(
    private val transactionService: TransactionService
): BaseDataSource() {

    suspend fun getAllTransactions() = getResult { transactionService.getAllTransactions() }
}