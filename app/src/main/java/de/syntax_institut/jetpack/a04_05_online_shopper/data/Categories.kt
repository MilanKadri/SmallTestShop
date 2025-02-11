package de.syntax_institut.jetpack.a04_05_online_shopper.data

class Category(
    val categoryName: String
)

val categories  = listOf(
    Category(categoryName = "All Products"),
    Category(categoryName = "Jewelery"),
    Category(categoryName = "Men's Wear"),
    Category(categoryName = "Women's Wear"),
    Category(categoryName = "Electronics")
)