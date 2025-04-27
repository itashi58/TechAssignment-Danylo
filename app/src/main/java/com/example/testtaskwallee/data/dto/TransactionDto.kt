package com.example.testtaskwallee.data.dto

data class TransactionDto(
    val transactionId: String, // Double or string
    val status: String,
    val amountTotals: AmountTotals,
    val transactionDetails: TransactionDetails
)

data class AmountTotals(
    val purchaseAmount: Double,
    val currency: String,
    val taxableAmount: Double,
    val taxRate: Double,
    val tipAmount: Double,
    val discountAmount: Double
)

data class TransactionDetails(
    val timestamp: String
)