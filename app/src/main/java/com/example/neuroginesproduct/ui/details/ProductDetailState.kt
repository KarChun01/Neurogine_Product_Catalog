package com.example.neuroginesproduct.ui.details

import com.example.neuroginesproduct.data.model.Product

data class ProductDetailState(
    val product: Product? = null,
    val productId: Int? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)