package com.example.neuroginesproduct.data.remote

import com.example.neuroginesproduct.data.model.Product
import com.example.neuroginesproduct.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): ProductResponse

    @GET("products/{id}")
    suspend fun getProduct(
        @Path("id") id: Int
    ): Product

    @GET("products/search")
    suspend fun searchProducts(
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): ProductResponse
}