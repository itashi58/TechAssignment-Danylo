package com.example.testtaskwallee.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Transaction(
    val id: String,
    val status: String,
    val amount: String,
    val tax: String,
    val date: String
) : Parcelable