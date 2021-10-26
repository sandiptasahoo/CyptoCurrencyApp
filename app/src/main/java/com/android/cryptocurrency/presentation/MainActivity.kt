package com.android.cryptocurrency.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.cryptocurrency.presentation.coin_list.CoinDetailsScreen
import com.android.cryptocurrency.presentation.coin_list.CoinListScreen
import com.android.cryptocurrency.presentation.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ) {
                        composable(
                            route = Screen.CoinListScreen.route
                        ){
                            CoinListScreen(navController)
                        }
                        composable(
                            route = Screen.CoinDetailsScreen.route + "/{coinId}"
                        ){navBackStack->
                            val coinId = navBackStack.arguments?.getString("coinId") ?: ""
                            CoinDetailsScreen(coinId)
                        }
                    }
                }
            }
        }
    }
}
