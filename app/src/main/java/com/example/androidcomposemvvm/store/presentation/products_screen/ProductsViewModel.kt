package com.example.androidcomposemvvm.store.presentation.products_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.androidcomposemvvm.store.domain.model.Product
import com.example.androidcomposemvvm.store.domain.repository.ProductsRepository
import com.example.androidcomposemvvm.store.presentation.util.sendEvent
import com.example.androidcomposemvvm.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val errorMessage: String) : Result<Nothing>()
}

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProductsViewState())
    val state = _state.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val result: Either<Unit, List<Product>> = productsRepository.getProducts()
            when (result) {
                is Either.Right -> {
                    _state.value = _state.value.copy(products = result.value)
                }
                is Either.Left -> {
                    val errorMessage = "Failed to retrieve products"
                    _state.value = _state.value.copy(error = errorMessage)
                    sendEvent(Event.Toast(errorMessage))
                }
            }
            _state.value = _state.value.copy(isLoading = false)
        }
    }
}
