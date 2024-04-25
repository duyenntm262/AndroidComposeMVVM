package com.example.androidcomposemvvm.store.data.remote

import com.example.androidcomposemvvm.store.domain.model.Product
import retrofit2.http.GET

interface ProductsApi {

    @GET("products")
    suspend fun getProducts(): List<Product>

}