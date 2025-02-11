package de.syntax_institut.jetpack.a04_05_online_shopper.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.Product

@Composable
fun ProductEntry(
    modifier: Modifier = Modifier,
    product: Product
) {
    Card(
        modifier = modifier
            .padding(4.dp),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(75.dp)
                    .padding(8.dp),
                model = product.image,
                contentDescription = null
            )

            ListItem(
                headlineContent = {
                    Text(product.title)
                },
                supportingContent = {
                    Text(product.category)
                },
                trailingContent = {
                    Text(String.format("%.2f€", product.price))
                }
            )
        }
    }
}