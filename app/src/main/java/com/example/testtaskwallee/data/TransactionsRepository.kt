package com.example.testtaskwallee.data

import com.example.testtaskwallee.data.dto.TransactionDto
import com.example.testtaskwallee.domain.ITransactionsRepository
import javax.inject.Inject

class TransactionsRepository @Inject constructor(
    private val transactionsApiService: TransactionsApiService
) : ITransactionsRepository {
    override suspend fun createTransaction(): TransactionDto {
        return transactionsApiService.createTransaction()
    }
}