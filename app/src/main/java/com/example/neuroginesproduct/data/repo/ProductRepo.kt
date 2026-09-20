package com.example.neuroginesproduct.data.repo

import com.example.neuroginesproduct.data.model.Product
import com.example.neuroginesproduct.data.model.ProductResponse
import com.example.neuroginesproduct.data.remote.ProductApi

class ProductRepo (private val api: ProductApi) {
    suspend fun getProducts( limit: Int, skip: Int): ProductResponse {
        return api.getProducts(limit, skip)
    }

    suspend fun getProduct(id: Int): Product {
        return api.getProduct(id)
    }

    suspend fun searchProducts( query: String, limit: Int, skip: Int): ProductResponse {
        return api.searchProducts(query, limit, skip)
    }
}