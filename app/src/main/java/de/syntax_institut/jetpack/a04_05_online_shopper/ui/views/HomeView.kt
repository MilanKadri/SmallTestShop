package de.syntax_institut.jetpack.a04_05_online_shopper.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import de.syntax_institut.jetpack.a04_05_online_shopper.data.viewmodels.ViewModelApi
import de.syntax_institut.jetpack.a04_05_online_shopper.ui.components.ProductEntry

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    viewModel: ViewModelApi,
) {
    val products by viewModel.productState.collectAsState()
    val alertState by viewModel.alertState.collectAsState()

    Column(modifier) {
        if (alertState) {
            AlertDialog(
                onDismissRequest = {
                    viewModel.dismissAlert()
                },
                confirmButton = {
                    Button(onClick = {
                        viewModel.dismissAlert()
                    }) {
                        Text("OK")
                    }
                },
                title = {
                    Text("Error")
                },
                text = {
                    Text("Failed to load products. Please try again.")
                }
            )
        } else {
            LazyColumn {
                items(products) { product ->
                    ProductEntry(
                        product = product
                    )
                }
            }
        }
    }
}