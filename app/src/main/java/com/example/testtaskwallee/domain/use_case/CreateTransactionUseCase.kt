package com.example.testtaskwallee.domain.use_case

import com.example.testtaskwallee.common.Resource
import com.example.testtaskwallee.domain.TransactionFactory
import com.example.testtaskwallee.domain.model.Transaction
import com.example.testtaskwallee.domain.repository.ITransactionsRepository
import com.example.testtaskwallee.presentation.use_cases.ICreateTransactionUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.math.BigDecimal
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val transactionsRepository: ITransactionsRepository,
    private val transactionFactory: TransactionFactory
) : ICreateTransactionUseCase {

    override suspend operator fun invoke(value: BigDecimal): Flow<Resource<Transaction>> =
        flow {
            try {
                emit(Resource.Loading<Transaction>())
                val transactionDto = transactionsRepository.createTransaction()
                emit(
                    Resource.Success<Transaction>(
                        transactionFactory.createTransaction(transactionDto)
                    )
                )
            } catch (e: Exception) {
                emit(
                    Resource.Error<Transaction>(e.localizedMessage ?: "An unexpected error occurred")
                )
            }
        }
}
