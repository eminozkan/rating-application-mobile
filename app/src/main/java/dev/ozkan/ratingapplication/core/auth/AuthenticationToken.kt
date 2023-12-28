package dev.ozkan.ratingapplication.core.auth

object AuthenticationToken {
    var token : String = ""

    fun getBearerToken() : String{
        return "Bearer $token"
    }
}