package com.example.neuroginesproduct.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neuroginesproduct.data.repo.ProductRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val repo: ProductRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailState())

    val uiState: StateFlow<ProductDetailState> = _uiState.asStateFlow()

    fun loadProduct(id: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    productId = id,
                    isLoading = true,
                    error = null
                )
            }

            try {
                val product = repo.getProduct(id)

                _uiState.update {
                    it.copy(
                        product = product,
                        isLoading = false
                    )
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Something went wrong"
                    )
                }
            }
        }
    }
}