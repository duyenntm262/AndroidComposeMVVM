package com.example.androidcomposemvvm.store.domain.repository

import arrow.core.Either
import com.example.androidcomposemvvm.store.domain.model.Product

interface ProductsRepository {

    suspend fun getProducts(): Either<Unit, List<Product>>

}
