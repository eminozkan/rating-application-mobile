package dev.ozkan.ratingapplication.core.auth.dto.user

data class GetUserResponse(
    val maskedEmail: String,
    val name: String,
    val userType: String
)