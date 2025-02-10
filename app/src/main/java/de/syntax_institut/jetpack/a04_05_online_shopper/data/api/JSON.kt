package de.syntax_institut.jetpack.a04_05_online_shopper.data.api


//data class Products(
//    val products: List<Product>
//)

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
)

data class Rating(
    val rate: Double,
    val count: Int
)