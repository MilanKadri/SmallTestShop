package de.syntax_institut.jetpack.a04_05_online_shopper.data.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.Product
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.ProductsAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModel: ViewModel() {
    private val api = ProductsAPI.retrofitService

    private val _productState = MutableStateFlow<List<Product>>(listOf())
    val productState = _productState.asStateFlow()



    init {
        loadAllProducts()
    }

    fun loadAllProducts() {
        viewModelScope.launch {
            try {
                val response = api.getProducts()
                val newProducts = _productState.value + response
                _productState.value = newProducts
            } catch (e: Exception) {
                Log.e("loadAllProducts", "Error: $e")
            }
        }
    }
}
