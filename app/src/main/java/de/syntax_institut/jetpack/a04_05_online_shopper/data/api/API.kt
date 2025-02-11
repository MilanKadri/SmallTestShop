package de.syntax_institut.jetpack.a04_05_online_shopper.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://fakestoreapi.com/"

val loggingInterceptor = HttpLoggingInterceptor().apply {
    // Logging Levels: BODY, BASIC, NONE, HEADERS
    level = HttpLoggingInterceptor.Level.BODY
}

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()


interface APIService {

    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("products/category/electronics")
    suspend fun getCategoryElectronics(): List<Product>

    @GET("products/category/jewelery")
    suspend fun getCategoryJewelery(): List<Product>

    @GET("products/category/men's clothing")
    suspend fun getCategoryMensClothing(): List<Product>

    @GET("products/category/women's clothing")
    suspend fun getCategoryWomensClothing(): List<Product>
}

object ProductsAPI {
    val retrofitService: APIService by lazy { retrofit.create(APIService::class.java) }
}

// Testfunktion
fun main() = runBlocking {
    val api = ProductsAPI.retrofitService

    try {
        val response = api.getCategoryJewelery()
        println("API Response: ${response}")
    } catch (e: Exception) {
        println("API Error: ${e.message}")
    }
}