package com.example.testtaskwallee.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testtaskwallee.domain.model.Transaction
import com.example.testtaskwallee.presentation.navigation.NavigationRoutes
import com.example.testtaskwallee.presentation.pinpad.PinPadScreen
import com.example.testtaskwallee.presentation.receipt.ReceiptDetailsScreen
import com.example.testtaskwallee.presentation.theme.TestTaskWalleeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val stateHandleTransactionKey = "transaction"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTaskWalleeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = NavigationRoutes.PinPad.route
                    ) {
                        composable(NavigationRoutes.PinPad.route) {
                            PinPadScreen(
                                paddingValues = innerPadding,
                                onReceiptNavigation = { transaction ->
                                    navController.currentBackStackEntry?.savedStateHandle?.set(
                                        stateHandleTransactionKey,
                                        transaction
                                    )
                                    navController.navigate(NavigationRoutes.Receipt.route)
                                }
                            )
                        }

                        composable(NavigationRoutes.Receipt.route) {
                            val transaction =
                                navController.previousBackStackEntry?.savedStateHandle?.get<Transaction>(
                                    stateHandleTransactionKey
                                )
                            BackHandler { finish() }
                            ReceiptDetailsScreen(
                                paddingValues = innerPadding,
                                transaction = transaction
                            )
                        }
                    }
                }
            }
        }
    }
}