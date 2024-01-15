package dev.ozkan.ratingapplication.core.home.dto.comment

data class CommentResponseItem(
    val commentId: String,
    val commentText: String,
    val createdAt: String,
    val rating: String,
    val usageTime: String,
    val userId : String
)