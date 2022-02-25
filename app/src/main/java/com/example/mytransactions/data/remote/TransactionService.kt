package com.example.mytransactions.data.remote

import com.example.mytransactions.data.model.TransactionList
import retrofit2.Response
import retrofit2.http.GET

interface TransactionService {
    @GET("test-data.json")
    suspend fun getAllTransactions() : Response<TransactionList>
}