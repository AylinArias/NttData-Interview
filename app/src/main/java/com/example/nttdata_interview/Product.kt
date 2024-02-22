package com.example.nttdata_interview

import retrofit2.http.GET

import java.io.Serializable

// datos del producto
data class Product(
    val title: String,
    val thumbnail: String,
    val description: String
) : Serializable

// lista de productos
data class ProductResponse(val products: List<Product>)

// métodos para realizar llamada a la API
interface ApiService {

    // GET para indicar el tipo de solicitud
    @GET("products")
    suspend fun getProducts(): ProductResponse
}

