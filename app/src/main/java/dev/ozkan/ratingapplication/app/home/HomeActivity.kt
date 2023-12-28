package dev.ozkan.ratingapplication.app.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dev.ozkan.ratingapplication.core.auth.dto.SessionResponse
import dev.ozkan.ratingapplication.core.auth.AuthenticationToken
import dev.ozkan.ratingapplication.core.retrofit.MainRetrofitInstance
import dev.ozkan.ratingapplication.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val call = MainRetrofitInstance.api.getSession(AuthenticationToken.getBearerToken())

        call.enqueue(object : Callback<SessionResponse>{
            override fun onResponse(
                call: Call<SessionResponse>,
                response: Response<SessionResponse>
            ) {
                if(response.isSuccessful){
                    binding.infoText.text = "Id : ${response.body()!!.id} , Email : ${response.body()!!.email}"
                }else{
                    Log.d("Emin", "Test failed")
                }

            }

            override fun onFailure(call: Call<SessionResponse>, t: Throwable) {
                Log.d("Emin", "Test failed ${t.message}")
            }
        })


}
}