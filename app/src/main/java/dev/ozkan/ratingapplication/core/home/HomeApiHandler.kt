package dev.ozkan.ratingapplication.core.home

import dev.ozkan.ratingapplication.core.auth.dto.SessionResponse
import dev.ozkan.ratingapplication.core.auth.dto.user.GetUserResponse
import dev.ozkan.ratingapplication.core.home.dto.comment.AddCommentRequestBody
import dev.ozkan.ratingapplication.core.home.dto.comment.AddCommentResponse
import dev.ozkan.ratingapplication.core.home.dto.comment.CommentResponse
import dev.ozkan.ratingapplication.core.home.dto.product.ProductResponse
import dev.ozkan.ratingapplication.core.home.dto.product.ProductResponseItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeApiHandler {

    @GET("/api/me/session")
    fun getSession(@Header ("Authorization") bearerToken : String) : Call<SessionResponse>


    @GET("/api/me")
    fun getUser(@Header ("Authorization") bearerToken : String) : Call<GetUserResponse>
    @GET("/api/products")
    fun getProducts(@Header ("Authorization") bearerToken: String) : Call<ProductResponse>

    @GET("/api/products/{productId}")
    fun getProduct(@Header ("Authorization") bearerToken : String,@Path("productId") productId : String) : Call<ProductResponseItem>

    @GET("/api/comments/{productId}")
    fun getComments(@Header ("Authorization") bearerToken: String, @Path("productId") productId : String) : Call<CommentResponse>

    @POST("/api/comments")
    fun addComment(@Header ("Authorization") bearerToken: String, @Body requestBody : AddCommentRequestBody) : Call<AddCommentResponse>

    @DELETE("/api/comments/{commentId}")
    fun deleteComment(@Header ("Authorization") bearerToken: String, @Path("commentId") commentId : String)
}