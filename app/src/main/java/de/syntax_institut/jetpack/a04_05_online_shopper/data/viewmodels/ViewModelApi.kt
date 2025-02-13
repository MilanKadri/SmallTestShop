package de.syntax_institut.jetpack.a04_05_online_shopper.data.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        loadAllProducts(count = "")
    }

    fun dismissAlert() {
        _alertState.value = false
    }

    fun loadAllProducts(count: String){
        viewModelScope.launch {
            try {
                val response = api.getProducts(count = count)
                _productState.value = response
            } catch (e: Exception) {
                Log.e("loadAllProducts", "Error: $e")
                _alertState.value = true
            }
        }
    }

    fun loadMensWear(count: String) {
        viewModelScope.launch {
            try {
                val response = api.getCategoryMensClothing(count = count)
                _productState.value = response
            } catch (e: Exception) {
                Log.e("loadMensWear", "Error $e")
            }
        }
    }

    fun loadWomensWear(count: String) {
        viewModelScope.launch {
            try {
                val response = api.getCategoryWomensClothing(count = count)
                _productState.value = response
            } catch (e: Exception) {
                Log.e("loadWomensWear", "Error $e")
            }
        }
    }

    fun loadJewelery(count: String) {
        viewModelScope.launch {
            try {
                val response = api.getCategoryJewelery(count = count)
                _productState.value = response
            } catch (e: Exception) {
                Log.e("loadJewelery", "Error $e")
            }
        }
    }

    fun loadElectronics(count: String) {
        viewModelScope.launch {
            try {
                val response = api.getCategoryElectronics(count = count)
                _productState.value = response
            } catch (e: Exception) {
                Log.e("LoadElectronics", "Error $e")
            }
        }
    }
}
