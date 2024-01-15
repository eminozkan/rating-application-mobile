package dev.ozkan.ratingapplication.app.home.recyclerviewadapter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.ozkan.ratingapplication.R
import dev.ozkan.ratingapplication.app.auth.AuthenticationActivity
import dev.ozkan.ratingapplication.core.auth.Session
import dev.ozkan.ratingapplication.core.auth.dto.user.GetUserResponse
import dev.ozkan.ratingapplication.core.retrofit.MainRetrofitInstance
import dev.ozkan.ratingapplication.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)

        val call = MainRetrofitInstance.api.getUser(Session.getBearerToken())
        call.enqueue(object : Callback<GetUserResponse> {
            override fun onResponse(
                call: Call<GetUserResponse>,
                response: Response<GetUserResponse>
            ) {
                if (response.isSuccessful){
                    binding.username.text = response.body()!!.name
                    binding.email.text = response.body()!!.maskedEmail
                    binding.usertype.text = response.body()!!.userType
                }
            }

            override fun onFailure(call: Call<GetUserResponse>, t: Throwable) {
                Log.e("Get User Response","${t.message}")
            }

        })

        binding.btnLogout.setOnClickListener{
            val intent = Intent(context,AuthenticationActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

}