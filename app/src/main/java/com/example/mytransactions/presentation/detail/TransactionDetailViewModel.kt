package com.example.mytransactions.presentation.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mytransactions.data.model.Transaction
import com.example.mytransactions.domain.TransactionDetailUseCase

class TransactionDetailViewModel @ViewModelInject constructor(
    private val transactionDetailUseCase: TransactionDetailUseCase
) : ViewModel() {

    fun find(id: Int): LiveData<Transaction> {
        return transactionDetailUseCase.getTransactionDetail(id)
    }

}
