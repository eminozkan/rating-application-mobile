package dev.ozkan.ratingapplication.app.home.product

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ozkan.ratingapplication.app.home.HomeActivity
import dev.ozkan.ratingapplication.app.home.comment.AddCommentActivity
import dev.ozkan.ratingapplication.app.home.comment.CommentRecyclerViewAdapter
import dev.ozkan.ratingapplication.app.home.models.Comments
import dev.ozkan.ratingapplication.core.auth.Session
import dev.ozkan.ratingapplication.core.home.dto.comment.CommentResponse
import dev.ozkan.ratingapplication.core.home.dto.product.ProductResponseItem
import dev.ozkan.ratingapplication.core.home.selected.SelectedItem
import dev.ozkan.ratingapplication.core.retrofit.MainRetrofitInstance
import dev.ozkan.ratingapplication.databinding.ActivityProductBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    private lateinit var commentArrayList: ArrayList<Comments>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectedProductImage.setImageBitmap(SelectedItem.productBitmap)
        binding.selectedProductHeader.text = SelectedItem.header
        val call = MainRetrofitInstance.api.getProduct(Session.getBearerToken(),SelectedItem.productId)
        call.enqueue(object : Callback<ProductResponseItem> {
            override fun onResponse(
                call: Call<ProductResponseItem>,
                response: Response<ProductResponseItem>
            ) {
                if (response.isSuccessful){
                    binding.selectedProductRating.text = response.body()!!.ratingOutOfFive.toString()
                }
            }

            override fun onFailure(call: Call<ProductResponseItem>, t: Throwable) {
                Log.e("Item Response", "${t.message}")
            }

        })

        getCommentsFromAPI()
        binding.btnAddComment.setOnClickListener{
            val intent = Intent(this,AddCommentActivity::class.java)
            startActivity(intent)
        }
    }


    private fun getCommentsFromAPI() {
        val call = MainRetrofitInstance.api.getComments(Session.getBearerToken(),SelectedItem.productId)

        call.enqueue(object : Callback<CommentResponse> {
            override fun onResponse(
                call: Call<CommentResponse>,
                response: Response<CommentResponse>
            ) {
                if (response.isSuccessful) {
                    binding.commentRecyclerView.layoutManager =
                        LinearLayoutManager(this@ProductActivity)
                    binding.commentRecyclerView.setHasFixedSize(true)
                    commentArrayList = arrayListOf()
                    convertResponseBodyToCommentItems(response)
                }else{
                    Log.d("comment response", "response is failed ${response.errorBody()!!.toString()}")
                }
            }

            override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                Log.d("comment response", "response is failed ${t.message}")
            }

        })
    }


    private fun convertResponseBodyToCommentItems(response: Response<CommentResponse>) {
        for (comment in response.body()!!) {
            val c = Comments(
                comment.commentId,
                comment.commentText,
                comment.rating,
                comment.usageTime,
                comment.createdAt,
                comment.userId
            )
            commentArrayList.add(c)
        }

        binding.commentRecyclerView.adapter = CommentRecyclerViewAdapter(commentArrayList)
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}