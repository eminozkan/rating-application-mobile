package dev.ozkan.ratingapplication.core.auth

object Session {
    var token : String = ""

    fun getBearerToken() : String{
        return "Bearer $token"
    }

    var userId : String = ""

    var userEmail : String = ""
}