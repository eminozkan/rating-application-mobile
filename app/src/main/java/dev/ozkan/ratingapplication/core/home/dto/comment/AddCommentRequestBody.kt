package dev.ozkan.ratingapplication.core.home.dto.comment

data class AddCommentRequestBody(var productId : String, var commentText : String, var rating : Int, var usageTime : String)
