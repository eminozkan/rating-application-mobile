package dev.ozkan.ratingapplication.app.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import dev.ozkan.ratingapplication.R
import dev.ozkan.ratingapplication.app.home.recyclerviewadapter.ProfileFragment
import dev.ozkan.ratingapplication.core.auth.Session
import dev.ozkan.ratingapplication.core.auth.dto.SessionResponse
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

        val productList = ProductsFragment()
        val profile = ProfileFragment()

        makeCurrentFragment(productList)
        val btmNav = binding.btmNav
        btmNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_product -> makeCurrentFragment(productList)
                R.id.ic_profile -> makeCurrentFragment(profile)
            }
            true
        }

        var call = MainRetrofitInstance.api.getSession(Session.getBearerToken())
        call.enqueue(object : Callback<SessionResponse> {
            override fun onResponse(
                call: Call<SessionResponse>,
                response: Response<SessionResponse>
            ) {
                if (response.isSuccessful){
                    Session.userId = response.body()!!.id
                    Session.userEmail = response.body()!!.email
                }
            }

            override fun onFailure(call: Call<SessionResponse>, t: Throwable) {
                Log.e("Session","${t.message}")
            }

        })
    }

    private fun makeCurrentFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView,fragment)
            commit()
        }
    }

}