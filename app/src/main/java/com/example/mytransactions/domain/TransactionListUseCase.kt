package com.example.mytransactions.domain

import com.example.mytransactions.data.repository.TransactionRepository
import com.example.mytransactions.utils.performGetOperation
import javax.inject.Inject

class TransactionListUseCase @Inject constructor(private val repository: TransactionRepository) {

    fun getTransactions() = performGetOperation(
        databaseQuery = { repository.getLocalTransactions() },
        networkCall = { repository.getRemoteTransactions() },
        saveCallResult = { repository.saveTransactions(it.transactionList) }
    )

}