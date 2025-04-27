package com.example.testtaskwallee.domain.use_case

import com.example.testtaskwallee.domain.TransactionFactory
import com.example.testtaskwallee.domain.model.Transaction
import com.example.testtaskwallee.domain.repository.ITransactionsRepository
import com.example.testtaskwallee.presentation.use_cases.ICreateTransactionUseCase
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val transactionsRepository: ITransactionsRepository,
    private val transactionFactory: TransactionFactory
) : ICreateTransactionUseCase {

    override suspend operator fun invoke(): Transaction {
        val transactionDto = transactionsRepository.createTransaction()
        return transactionFactory.createTransaction(transactionDto)
    }
}