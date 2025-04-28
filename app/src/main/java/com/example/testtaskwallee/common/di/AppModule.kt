package com.example.testtaskwallee.common.di

import com.example.testtaskwallee.data.TransactionsApiService
import com.example.testtaskwallee.data.TransactionsRepository
import com.example.testtaskwallee.domain.TransactionFactory
import com.example.testtaskwallee.domain.use_case.CreateTransactionUseCase
import com.example.testtaskwallee.presentation.use_cases.ICreateTransactionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideBaseUrl(): String = "https://jason-koala.wallee.workers.dev/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideTransactionsApiService(retrofit: Retrofit): TransactionsApiService =
        retrofit.create(TransactionsApiService::class.java)

    @Provides
    @Singleton
    fun provideCreateTransactionUseCase(
        transactionsRepository: TransactionsRepository,
        transactionFactory: TransactionFactory
    ): ICreateTransactionUseCase {
        return CreateTransactionUseCase(transactionsRepository, transactionFactory)
    }
}