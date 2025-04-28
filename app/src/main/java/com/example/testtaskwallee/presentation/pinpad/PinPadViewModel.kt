package com.example.testtaskwallee.presentation.pinpad

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskwallee.common.Resource
import com.example.testtaskwallee.presentation.use_cases.ICreateTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class PinPadViewModel @Inject constructor(
    private val createTransactionUseCase: ICreateTransactionUseCase,
): ViewModel() {

    private var pinpadValueDecimal: BigDecimal = BigDecimal.ZERO

    private val _pinpadValue: MutableState<String> = mutableStateOf("0.00")
    val pinpadValue: State<String> = _pinpadValue

    private val _state: MutableState<PinPadScreenState> = mutableStateOf(PinPadScreenState())
    val state: State<PinPadScreenState> = _state

    @SuppressLint("DefaultLocale")
    fun pinPadButtonClicked(value: String) {
        pinpadValueDecimal = pinpadValueDecimal.scaleByPowerOfTen(1) + value.toBigDecimal().divide(BigDecimal.valueOf(100.0))
        _pinpadValue.value = String.format("%.2f", (pinpadValueDecimal))
    }

    fun onOkButtonClicked() {
        viewModelScope.launch {
            createTransactionUseCase(pinpadValueDecimal).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = PinPadScreenState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _state.value = PinPadScreenState(isLoading = true, transaction = result.data)
                        this.cancel()
                    }

                    is Resource.Error -> {
                        _state.value =
                            PinPadScreenState(error = result.message ?: "An unexpected error occurred")
                    }
                }
            }
        }
    }
}