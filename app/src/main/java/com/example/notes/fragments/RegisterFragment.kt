package com.example.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.databinding.FragmentRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    lateinit var binding : FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRegisterBinding.inflate(inflater)

        binding.registerButton.setOnClickListener {
            register()
        }


        return binding.root
    }



    private fun register() {
        val auth = Firebase.auth
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            auth.createUserWithEmailAndPassword(
                email, password
            )
                .addOnSuccessListener {
                    val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                    findNavController().navigate(action)
                }
                .addOnFailureListener {
                    Toast.makeText(
                        context,
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
        } else {
            Toast.makeText(
                context,
                getString(R.string.Fill_fields),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}