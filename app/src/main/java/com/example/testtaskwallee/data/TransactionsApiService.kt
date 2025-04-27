package com.example.testtaskwallee.data

import com.example.testtaskwallee.data.dto.TransactionDto
import retrofit2.http.GET

interface TransactionsApiService {
    @GET()
    suspend fun createTransaction(): TransactionDto
}