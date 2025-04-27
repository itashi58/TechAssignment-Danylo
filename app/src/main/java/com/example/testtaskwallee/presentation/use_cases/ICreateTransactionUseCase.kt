package com.example.testtaskwallee.presentation.use_cases

import com.example.testtaskwallee.domain.model.Transaction

interface ICreateTransactionUseCase {
    suspend operator fun invoke(): Transaction
}