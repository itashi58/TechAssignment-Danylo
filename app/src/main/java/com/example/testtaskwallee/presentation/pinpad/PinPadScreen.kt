package com.example.testtaskwallee.presentation.pinpad

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testtaskwallee.R
import com.example.testtaskwallee.domain.model.Transaction

private val pinpadLayout = listOf(
    listOf("1", "2", "3"),
    listOf("4", "5", "6"),
    listOf("7", "8", "9"),
    listOf("", "0", "OK")
)

@Composable
fun PinPadScreen(
    paddingValues: PaddingValues,
    pinPadViewModel: PinPadViewModel = hiltViewModel(),
    onReceiptNavigation: (Transaction?) -> Unit
) {
    val screenState = pinPadViewModel.state.value

    if (screenState.transaction != null) {
        onReceiptNavigation(screenState.transaction)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(colorResource(R.color.white))
    ) {
        if (screenState.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(100.dp),
                    color = colorResource(R.color.purple_500),
                    strokeWidth = 4.dp
                )
            }
            return
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (screenState.error.isNotEmpty()) {
                Text(
                    text = screenState.error,
                    color = colorResource(R.color.red),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                )
            }

            Text(
                text = "Purchase",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 32.dp)
            )

            Text(
                text = "Please enter amount.",
                fontSize = 20.sp,
                color = Gray,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = pinPadViewModel.pinpadValue.value,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(
                        width = 1.dp,
                        color = colorResource(R.color.gray),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
        NumPad(
            onNumberClick = { number ->
                pinPadViewModel.pinPadButtonClicked(number)
            },
            onOkClick = {
                pinPadViewModel.onOkButtonClicked()
            }
        )
    }
}


@Composable
fun NumPad(
    onNumberClick: (String) -> Unit,
    onOkClick: () -> Unit
) {
    // Number Pad
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(R.color.pin_pad_background)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (row in pinpadLayout) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (item in row) {
                    if (item == "OK") {
                        NumPadOkButton(
                            modifier = Modifier.weight(1f),
                            item = item,
                            onClick = onOkClick
                        )
                    } else {
                        NumPadButton(
                            modifier = Modifier.weight(1f),
                            item = item,
                            onClick = onNumberClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NumPadButton(
    modifier: Modifier,
    item: String,
    onClick: (String) -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxSize(),
        onClick = { onClick(item) },
        colors = ButtonColors(
            containerColor = colorResource(R.color.pin_pad_background),
            contentColor = colorResource(R.color.pin_pad_number),
            disabledContainerColor = colorResource(R.color.pin_pad_background),
            disabledContentColor = colorResource(R.color.pin_pad_number)
        ),
        enabled = item.isNotEmpty()
    ) {
        Text(
            item,
            fontSize = 40.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun NumPadOkButton(
    modifier: Modifier,
    item: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonColors(
            containerColor = colorResource(R.color.pin_pad_button_background),
            contentColor = colorResource(R.color.white),
            disabledContainerColor = colorResource(R.color.pin_pad_button_background),
            disabledContentColor = colorResource(R.color.white)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            item,
            fontSize = 40.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PurchaseScreenPreview() {
    PinPadScreen(
        PaddingValues(),
        onReceiptNavigation = {}
    )
}