package com.example.androidcomposemvvm.store.data.repository

import arrow.core.Either
import com.example.androidcomposemvvm.store.data.maper.toGeneralError
import com.example.androidcomposemvvm.store.data.remote.ProductsApi
import com.example.androidcomposemvvm.store.domain.model.Product
import com.example.androidcomposemvvm.store.domain.repository.ProductsRepository
import javax.inject.Inject


class ProductsRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi
) : ProductsRepository {
    override suspend fun getProducts(): Either<Unit, List<Product>> {
        return Either.catch {
            productsApi.getProducts()
        }.mapLeft { it.toGeneralError() }
    }
}