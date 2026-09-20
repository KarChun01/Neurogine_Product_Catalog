package com.example.neuroginesproduct.data.model

import java.util.Objects

data class Product (
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val tags: List<String>,
    val brand: String,
    val weight: Int,
    val dimension: String,
    val warrantyInformation: String,
    val shippingInformation: String,
    val availabilityStatus: String,
    val returnPolicy: String,
    val minimumOrderQuantity: Int,
    val images: List<String>,
    val thumbnail: String,
    val sku: String,
    val barcode: String,
    val qrCode: String,
    val reviews: List<Review>,
    val meta: Objects
)

data class Review (
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String,
)
