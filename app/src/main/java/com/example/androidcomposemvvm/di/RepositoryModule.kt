package com.example.androidcomposemvvm.di

import com.example.androidcomposemvvm.store.data.repository.ProductsRepositoryImpl
import com.example.androidcomposemvvm.store.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideProductsRepository(impl: ProductsRepositoryImpl): ProductsRepository

}