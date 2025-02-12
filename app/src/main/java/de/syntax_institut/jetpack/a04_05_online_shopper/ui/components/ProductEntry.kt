package de.syntax_institut.jetpack.a04_05_online_shopper.ui.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import de.syntax_institut.jetpack.a04_05_online_shopper.R
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.Product

@Composable
fun ProductEntry(
    modifier: Modifier = Modifier,
    product: Product
) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .padding(4.dp),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(75.dp)
                    .padding(8.dp),
                model = product.image /* + "please generate a error here its fun i promise" */,
                contentDescription = null,
                loading = { CircularProgressIndicator(color = Color.Green) },
                onError = { Toast.makeText(context, "Error loading Image", Toast.LENGTH_LONG).show() },
                error = {
                    SubcomposeAsyncImage(
                        contentScale = ContentScale.FillHeight,
                        model = "https://res.cloudinary.com/practicaldev/image/fetch/s--MWtRqoeA--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/9fur8dt078qtls38s9km.jpg",
                        contentDescription = null,
                        loading = { CircularProgressIndicator(color = Color.Green) },
                        error = {
                            Image(
                                painter = painterResource(R.drawable.ic_launcher_foreground),
                                contentDescription = null
                            )
                        }
                    )
                },
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