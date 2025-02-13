package de.syntax_institut.jetpack.a04_05_online_shopper.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import de.syntax_institut.jetpack.a04_05_online_shopper.R
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
    val limits = listOf("1", "5", "10", "15", "20")

    var selectedLimit by remember { mutableStateOf("20") }
    var selectedCategory by remember { mutableStateOf("All Products") }
    var showDropdown by remember { mutableStateOf(false) }
    var showFilters by remember { mutableStateOf(false) }

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
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "YEEZY.COM",
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                style = MaterialTheme.typography.headlineMedium
            )
            IconButton(
                onClick = { showFilters = !showFilters }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_filter_alt_24),
                    contentDescription = null
                )
            }
        }
       AnimatedVisibility(visible = showFilters) {
           Row(
               modifier = Modifier
                   .padding(start = 12.dp),
           ) {
               Button(
                   onClick = { showDropdown = !showDropdown },
                   modifier = Modifier
                       .padding(end = 4.dp)
               ) {
                   Text(text = "Results: $selectedLimit")
               }

               DropdownMenu(
                   expanded = showDropdown,
                   onDismissRequest = { showDropdown = false }
               ) {
                   limits.forEach { limit ->
                       DropdownMenuItem(
                           modifier = Modifier
                               .width(45.dp)
                               .align(Alignment.Start),
                           text = { Text(limit) },
                           onClick = {
                               selectedLimit = limit
                               showDropdown = false
                           }
                       )
                   }
               }

               LazyRow(
                   modifier = Modifier
                       .padding(vertical = 1.dp)
                       .padding(horizontal = 12.dp)

               ) {
                   items(categories) { category ->
                       val isSelected = category == selectedCategory
                       Button(
                           modifier = Modifier
                               .padding(end = 4.dp),
                           onClick = { selectedCategory = category },
                           colors = ButtonDefaults.buttonColors(if (isSelected) Color.Gray else Color.LightGray)
                       ) {
                           Text(text = category)
                       }
                   }
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
