package dev.ozkan.ratingapplication.app.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import dev.ozkan.ratingapplication.R
import dev.ozkan.ratingapplication.core.auth.dto.error.ErrorResponse
import dev.ozkan.ratingapplication.core.auth.dto.register.RegisterData
import dev.ozkan.ratingapplication.core.auth.dto.register.RegisterResponse
import dev.ozkan.ratingapplication.core.retrofit.AuthRetrofitInstance
import dev.ozkan.ratingapplication.databinding.FragmentSignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp : Fragment() {
    private lateinit var binding : FragmentSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        binding.login.setOnClickListener{
            findNavController().navigate(R.id.action_signUp_to_login)
        }

        binding.btnSignup.setOnClickListener{
            val name = binding.name.text.toString()
            val email = binding.username.text.toString()
            val password = binding.password.text.toString()
            val data = RegisterData(name,email,password)

            val call = AuthRetrofitInstance.api.registerRequest(data)

            call.enqueue(object  : Callback<RegisterResponse>{
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(context, "Successfully Registered", Toast.LENGTH_LONG).show()
                    }else{
                        handleErrorResponse(response)
                    }
                }

                private fun handleErrorResponse(response: Response<RegisterResponse>) {
                    val gson = Gson()
                    try {
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        val errorMessage = errorResponse.message
                        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                    } catch (e: JsonSyntaxException) {
                        Log.e("Json Syntax Error", "Error : ${e.message}")
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.d("Register Failed","Register failed ${t.message}")
                }

            })


        }
        return binding.root
    }
    companion object {

    }
}