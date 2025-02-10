package de.syntax_institut.jetpack.a04_05_online_shopper.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.Product
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.ProductsAPI
import de.syntax_institut.jetpack.a04_05_online_shopper.data.viewmodels.ViewModelApi
import de.syntax_institut.jetpack.a04_05_online_shopper.ui.components.ProducEntry

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    viewModel: ViewModelApi,
) {
    val products by viewModel.productState.collectAsState()
    Column(modifier) {
        LazyColumn {
            items(products) { product ->
                ProducEntry(
                    product = product
                )
            }
        }
    }
}