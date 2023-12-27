package dev.ozkan.ratingapplication.app.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.ozkan.ratingapplication.R
import dev.ozkan.ratingapplication.databinding.FragmentSignUpBinding

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
        return binding.root
    }
    companion object {

    }
}