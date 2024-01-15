package dev.ozkan.ratingapplication.core.home.dto.product

data class ProductResponseItem(
    val base64Image: String,
    val category: String,
    val commentCount: Int,
    val externalURL: String,
    val id: String,
    val maker: String,
    val model: String,
    val ratingOutOfFive: Double
)