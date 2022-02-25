package com.example.mytransactions.presentation.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mytransactions.data.model.Transaction
import com.example.mytransactions.domain.TransactionListUseCase
import com.example.mytransactions.utils.Resource

class TransactionListViewModel @ViewModelInject constructor(
    private val transactionListUseCase: TransactionListUseCase
) : ViewModel() {

    val transactions: LiveData<Resource<List<Transaction>>> get() = transactionListUseCase.getTransactions()

}
