package com.example.testtaskwallee.presentation.receipt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testtaskwallee.R
import com.example.testtaskwallee.domain.model.Transaction

@Composable
fun ReceiptDetailsScreen(
    paddingValues: PaddingValues,
    transaction: Transaction?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 8.dp)
            .background(colorResource(R.color.white))
    ) {
        Text(
            text = "Purchase",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 32.dp)
        )
        Text(
            text = "Transaction ID: ${transaction?.id}",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "Status: ${transaction?.status}",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "Final Amount: ${transaction?.amount}",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "Tax: ${transaction?.tax}",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "Date: ${transaction?.date}",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }

}