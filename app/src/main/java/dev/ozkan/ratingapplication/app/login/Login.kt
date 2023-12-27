package dev.ozkan.ratingapplication.app.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.ozkan.ratingapplication.R
import dev.ozkan.ratingapplication.databinding.FragmentLoginBinding

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
        return binding.root
    }

    companion object {
    }
}