package com.example.testtaskwallee.domain.model

data class Transaction(
    val id: String,
    val status: String,
    val amount: String,
    val tax: String,
    val date: String
)