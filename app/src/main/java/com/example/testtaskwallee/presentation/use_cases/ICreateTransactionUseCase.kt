package com.example.testtaskwallee.presentation.use_cases

import com.example.testtaskwallee.common.Resource
import com.example.testtaskwallee.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

interface ICreateTransactionUseCase {
    suspend operator fun invoke(value: BigDecimal): Flow<Resource<Transaction>>
}