package com.example.testtaskwallee.domain

import com.example.testtaskwallee.data.dto.TransactionDto

interface ITransactionsRepository {
    suspend fun createTransaction(): TransactionDto
}