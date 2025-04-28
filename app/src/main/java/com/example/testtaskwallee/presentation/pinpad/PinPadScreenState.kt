package com.example.testtaskwallee.presentation.pinpad

import com.example.testtaskwallee.domain.model.Transaction

data class PinPadScreenState (
    val isLoading: Boolean = false,
    val transaction: Transaction? = null,
    val error: String = ""
)