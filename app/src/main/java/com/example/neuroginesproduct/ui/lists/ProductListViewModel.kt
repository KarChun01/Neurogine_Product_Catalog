package com.example.neuroginesproduct.ui.lists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neuroginesproduct.data.repo.ProductRepo
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductListViewModel (private val repo: ProductRepo): ViewModel() {
    private val _uiState = MutableStateFlow(ProductListState())

    val uiState: StateFlow<ProductListState> = _uiState.asStateFlow()
    private val pageSize = 20
    private var skip = 0
    private var searchJob: Job? = null

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            try {
                val response = repo.getProducts(
                    limit = pageSize,
                    skip = 0
                )

                skip = response.products.size

                _uiState.update {
                    it.copy(
                        products = response.products,
                        isLoading = false,
                        hasMore = response.products.size < response.total
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

    fun loadMoreProducts() {
        if (_uiState.value.isLoadingMore || !_uiState.value.hasMore) {
            return
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoadingMore = true)
            }

            try {

                val response = if (_uiState.value.searchQuery.isBlank()) {
                    repo.getProducts(
                        limit = pageSize,
                        skip = skip
                    )
                } else {
                    repo.searchProducts(
                        query = _uiState.value.searchQuery,
                        limit = pageSize,
                        skip = skip
                    )
                }

                skip += response.products.size

                _uiState.update {
                    it.copy(
                        products = it.products + response.products,
                        isLoadingMore = false,
                        hasMore = skip < response.total
                    )
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoadingMore = false,
                        error = e.message ?: "Failed to load more products"
                    )
                }
            }
        }
    }

    fun searchProducts(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }

        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            delay(500)

            if (query.isBlank()) {
                skip = 0
                loadProducts()
                return@launch
            }

            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            try {
                val response = repo.searchProducts(
                    query = query,
                    limit = pageSize,
                    skip = 0
                )

                skip = response.products.size

                _uiState.update {
                    it.copy(
                        products = response.products,
                        isLoading = false,
                        hasMore = skip  < response.total
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