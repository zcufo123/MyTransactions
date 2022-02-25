package com.example.mytransactions.domain

import com.example.mytransactions.data.repository.TransactionRepository
import javax.inject.Inject

class TransactionDetailUseCase @Inject constructor(private val repository: TransactionRepository) {

    fun getTransactionDetail(id: Int) = repository.getLocalTransaction(id)

}