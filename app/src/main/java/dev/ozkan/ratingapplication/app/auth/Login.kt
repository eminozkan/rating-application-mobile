package dev.ozkan.ratingapplication.app.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import dev.ozkan.ratingapplication.R
import dev.ozkan.ratingapplication.app.home.HomeActivity
import dev.ozkan.ratingapplication.app.auth.dto.login.LoginData
import dev.ozkan.ratingapplication.app.auth.dto.login.LoginResponse
import dev.ozkan.ratingapplication.core.auth.AuthenticationToken
import dev.ozkan.ratingapplication.core.retrofit.AuthRetrofitInstance
import dev.ozkan.ratingapplication.databinding.FragmentLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : Fragment() {

    private lateinit var binding : FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.signup.setOnClickListener{
            findNavController().navigate(R.id.action_login_to_signUp)
        }

        binding.btnLogin.setOnClickListener{
            val email = binding.username.text
            val password = binding.password.text
            val request =  LoginData(email.toString(),password.toString())
            val call = AuthRetrofitInstance.api.loginRequest(request)

            call.enqueue(object : Callback<LoginResponse>{
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful){
                        AuthenticationToken.token = response.body()!!.token;
                        val intent = Intent(context, HomeActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(context, "Invalid credentials", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d("Authentication Fail","Authentication Failed" + t.message)
                }

            })
        }

        return binding.root
    }

    companion object {
    }
}