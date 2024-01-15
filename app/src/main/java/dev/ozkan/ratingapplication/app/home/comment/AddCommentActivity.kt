package dev.ozkan.ratingapplication.app.home.comment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import dev.ozkan.ratingapplication.app.home.product.ProductActivity
import dev.ozkan.ratingapplication.core.auth.Session
import dev.ozkan.ratingapplication.core.auth.dto.error.ErrorResponse
import dev.ozkan.ratingapplication.core.home.dto.comment.AddCommentRequestBody
import dev.ozkan.ratingapplication.core.home.dto.comment.AddCommentResponse
import dev.ozkan.ratingapplication.core.home.selected.SelectedItem
import dev.ozkan.ratingapplication.core.retrofit.MainRetrofitInstance
import dev.ozkan.ratingapplication.databinding.ActivityAddCommentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCommentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCommentBinding
    private lateinit var ratingData: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ratingSpinner: Spinner = binding.spinnerRating
        val ratingData = arrayOf("1", "2", "3", "4", "5")

        val ratingAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ratingData)
        ratingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        ratingSpinner.adapter = ratingAdapter

        val usageSpinner: Spinner = binding.spinnerUsageTime
        val usageData = arrayOf("0-3 Months", "3-6 Months", "6-12 Months", "12+ Months")

        val usageAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, usageData)
        usageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        usageSpinner.adapter = usageAdapter

        binding.btnAddComment.setOnClickListener {
            val commentText = binding.commentText.text.toString()
            val ratingValue = getRatingValueFromSpinner(ratingSpinner)
            val usageTime = getUsageTimeFromSpinner(usageSpinner)
            var isFieldsEmpty = checkFields(commentText,ratingValue,usageTime)

            if (!isFieldsEmpty){
                val data = AddCommentRequestBody(SelectedItem.productId,commentText,ratingValue,usageTime)
                val call = MainRetrofitInstance.api.addComment(Session.getBearerToken(),data)

                call.enqueue(object : Callback<AddCommentResponse> {
                    override fun onResponse(
                        call: Call<AddCommentResponse>,
                        response: Response<AddCommentResponse>
                    ) {
                        if (response.isSuccessful){
                            Toast.makeText(this@AddCommentActivity, "Comment added successfully", Toast.LENGTH_LONG).show()
                            val intent = Intent(this@AddCommentActivity,ProductActivity::class.java)
                            startActivity(intent)
                        }else{
                            handleErrorResponse(response)
                        }
                    }

                    private fun handleErrorResponse(response: Response<AddCommentResponse>) {
                        val gson = Gson()
                        try {
                            val errorResponse =
                                gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            Toast.makeText(this@AddCommentActivity, "$errorMessage", Toast.LENGTH_LONG).show()
                        } catch (e: JsonSyntaxException) {
                            Log.e("Json Syntax Error", "Error : ${e.message}")
                        }
                    }
                    override fun onFailure(call: Call<AddCommentResponse>, t: Throwable) {
                        Toast.makeText(this@AddCommentActivity, "Comment not added ${t.message}", Toast.LENGTH_LONG).show()
                    }

                })
            }
        }
    }

    private fun checkFields(commentText: String, ratingValue: Int, usageTime: String) : Boolean {
        if (commentText.isEmpty()){
            Toast.makeText(this, "Comment text can not be empty", Toast.LENGTH_LONG).show()
            return true
        }

        if (ratingValue == 0){
            Toast.makeText(this, "Select Rating Value", Toast.LENGTH_LONG).show()
            return true
        }

        if (usageTime.isEmpty()){
            Toast.makeText(this, "Select usage Time", Toast.LENGTH_LONG).show()
            return true
        }
        return false
    }

    private fun getUsageTimeFromSpinner(usageSpinner: Spinner): String {
        return when (binding.spinnerUsageTime.selectedItem.toString()) {
            "0-3 Months" ->  "0-3"
            "3-6 Months"-> "3-6"
            "6-12 Months" -> "6-12"
            "12+ Months" -> "12+"
            else -> " "
        }
    }

    private fun getRatingValueFromSpinner(spinner: Spinner): Int {
        return when (spinner.selectedItem.toString()) {
            "1" ->  1
            "2" -> 2
            "3" -> 3
            "4" -> 4
            "5" -> 5
            else -> 0
        }
    }


}