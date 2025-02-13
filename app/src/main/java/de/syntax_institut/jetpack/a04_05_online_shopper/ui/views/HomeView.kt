package de.syntax_institut.jetpack.a04_05_online_shopper.ui.views

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.syntax_institut.jetpack.a04_05_online_shopper.data.viewmodels.ViewModelApi
import de.syntax_institut.jetpack.a04_05_online_shopper.ui.components.ProductEntry

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    viewModel: ViewModelApi,
) {
    val products by viewModel.productState.collectAsState()
    val alertState by viewModel.alertState.collectAsState()
    val categories = listOf("All Products", "Electronics", "Jewelery", "Men's Wear", "Women's Wear")
    val limitis = listOf("1", "5", "10", "15", "20")

    var selectedLimit by remember { mutableStateOf("20") }
    var selectedCategory by remember { mutableStateOf("All Products") }

    LaunchedEffect(selectedCategory, selectedLimit) {
        when (selectedCategory) {
            "All Products" -> viewModel.loadAllProducts(selectedLimit)
            "Electronics" -> viewModel.loadElectronics(selectedLimit)
            "Jewelery" -> viewModel.loadJewelery(selectedLimit)
            "Men's Wear" -> viewModel.loadMensWear(selectedLimit)
            "Women's Wear" -> viewModel.loadWomensWear(selectedLimit)
        }
    }

    Column(modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "YEEZY.COM",
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                style = MaterialTheme.typography.headlineMedium
            )
        }

//        Row {
//            OutlinedTextField(
//                value = { selectedCategory },
//                onValueChange = { selectedCategory  },
//                placeholder = "HH-01"
//            )
//        }
        LazyRow(
            modifier = Modifier
                .padding(vertical = 1.dp)
                .padding(horizontal = 12.dp),

            ) {
            items(limitis) { limit ->
                val isSelected = limit == selectedLimit
                Button(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    onClick = { selectedLimit = limit },
                    colors = ButtonDefaults.buttonColors(if (isSelected) Color.Gray else Color.LightGray)
                ) {
                    Text(text = limit)
                }
            }
        }

        LazyRow(
            modifier = Modifier
                .padding(vertical = 1.dp)
                .padding(horizontal = 12.dp),

            ) {
            items(categories) { category ->
                val isSelected = category == selectedCategory
                Button(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    onClick = { selectedCategory = category },
                    colors = ButtonDefaults.buttonColors(if (isSelected) Color.Gray else Color.LightGray)
                ) {
                    Text(text = category)
                }
            }
        }

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