package com.example.testtaskwallee.data.dto

data class TransactionDto(
    val transactionId: String,
    val status: String,
    val amount: Amount,
    val transactionDetails: TransactionDetailsDto
)

data class Amount(
    val purchaseAmount: String,
    val currency: String,
    val taxableAmount: String,
    val taxRate: String,
    val tipAmount: String,
    val discountAmount: String
)

data class TransactionDetailsDto(
    val timestamp: String
)