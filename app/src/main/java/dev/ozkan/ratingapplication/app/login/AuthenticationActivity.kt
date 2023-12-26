package dev.ozkan.ratingapplication.app.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import dev.ozkan.ratingapplication.R
import dev.ozkan.ratingapplication.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAuthenticationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentContainerView = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = fragmentContainerView.navController
    }
}