package de.syntax_institut.jetpack.a04_05_online_shopper.data.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.Product
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.ProductsAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModelApi: ViewModel() {
    private val api = ProductsAPI.retrofitService

    private val _productState = MutableStateFlow<List<Product>>(listOf())
    val productState = _productState.asStateFlow()

    private val _alertState = MutableStateFlow(false)
    val alertState = _alertState.asStateFlow()




    init {
        loadAllProducts()
    }

    fun dismissAlert() {
        _alertState.value = false
        loadAllProducts()
    }

    fun loadAllProducts(){
        viewModelScope.launch {

            try {
                val response = api.getProducts()
                val newProduct = _productState.value + response
                _productState.value = newProduct
            } catch (e: Exception) {
                Log.e("loadAllProducts", "Error: $e")
                _alertState.value = true
            }
        }
    }

    fun loadMensWear() {
        viewModelScope.launch {
            try {
                val response = api.getCategoryMensClothing()
                val newProducts = _productState.value + response
                _productState.value = newProducts
            } catch (e: Exception) {
                Log.e("loadMensWear", "Error $e")
            }
        }
    }

    fun loadWomensWear() {
        viewModelScope.launch {
            try {
                val response = api.getCategoryWomensClothing()
                val newProducts = _productState.value + response
                _productState.value = newProducts
            } catch (e: Exception) {
                Log.e("loadWomensWear", "Error $e")
            }
        }
    }

    fun loadJewelery() {
        viewModelScope.launch {
            try {
                val response = api.getCategoryJewelery()
                val newProducts = _productState.value + response
                _productState.value = newProducts
            } catch (e: Exception) {
                Log.e("loadJewelery", "Error $e")
            }
        }
    }

    fun loadElectronics() {
        viewModelScope.launch {
            try {
                val response = api.getCategoryElectronics()
                val newProducts = _productState.value + response
                _productState.value = newProducts
            } catch (e: Exception) {
                Log.e("LoadElectronics", "Error $e")
            }
        }
    }
}
