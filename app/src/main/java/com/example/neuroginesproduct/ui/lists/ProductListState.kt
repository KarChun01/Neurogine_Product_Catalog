package com.example.neuroginesproduct.ui.lists

import com.example.neuroginesproduct.data.model.Product

data class ProductListState (
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null,
    val hasMore: Boolean = true
)
