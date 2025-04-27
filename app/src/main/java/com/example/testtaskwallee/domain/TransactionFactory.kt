package com.example.testtaskwallee.domain

import com.example.testtaskwallee.data.dto.AmountTotals
import com.example.testtaskwallee.data.dto.TransactionDto
import com.example.testtaskwallee.domain.model.Transaction
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionFactory @Inject constructor() {

    private val outputDateFormat = "HH:mm:ss dd-MM-yyyy"
    private val inputDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    fun createTransaction(transactionDto: TransactionDto): Transaction {
        return Transaction(
            id = transactionDto.transactionId,
            status = transactionDto.status,
            amount = "${calculateAmount(transactionDto.amountTotals)} ${transactionDto.amountTotals.currency}",
            tax = "${calculateTax(transactionDto.amountTotals)} ${transactionDto.amountTotals.currency}",
            date = formatTimestamp(transactionDto.transactionDetails.timestamp)
        )
    }

    private fun calculateAmount(amountTotals: AmountTotals): Double =
        amountTotals.purchaseAmount + amountTotals.tipAmount - amountTotals.discountAmount - calculateTax(amountTotals)

    private fun calculateTax(amountTotals: AmountTotals): Double =
        amountTotals.taxableAmount * amountTotals.taxRate

    fun formatTimestamp(timestamp: String): String {
        val inputFormat = SimpleDateFormat(inputDateFormat, Locale.getDefault())
        val outputFormat = SimpleDateFormat(outputDateFormat, Locale.getDefault())
        val date: Date = inputFormat.parse(timestamp)!!
        return outputFormat.format(date)
    }
}