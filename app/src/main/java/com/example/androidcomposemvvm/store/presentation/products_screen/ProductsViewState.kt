package com.example.androidcomposemvvm.store.presentation.products_screen

import com.example.androidcomposemvvm.store.domain.model.Product


data class ProductsViewState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)