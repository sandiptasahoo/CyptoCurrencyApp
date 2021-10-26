package com.android.cryptocurrency.presentation.coin_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.cryptocurrency.presentation.Screen
import com.android.cryptocurrency.presentation.coin_list.components.CoinListItem
import org.koin.androidx.compose.getViewModel

@Composable
fun CoinListScreen(
    navController: NavController,
    coinListViewModel: CoinListViewModel = getViewModel()
) {
    val state = coinListViewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.coins) { coin ->
                CoinListItem(coin = coin, onItemClick = {
                    navController.navigate(Screen.CoinDetailsScreen.route + "/${coin.id}")
                })
                Divider(modifier = Modifier.padding(4.dp))
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
