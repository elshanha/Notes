package com.example.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.databinding.FragmentLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater)

        binding.loginButton.setOnClickListener {
            login()
        }

        binding.registerButton.setOnClickListener {
            openRegister()
        }

        return binding.root
    }

    private fun login() {

        var email = binding.emailEditText.text.toString()
        var password = binding.passwordEditText.text.toString()

        val auth = Firebase.auth
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            auth.signInWithEmailAndPassword(
                email, password
            )
                .addOnSuccessListener {
                    openHome()
                }
                .addOnFailureListener {
                    Toast.makeText(
                        context,
                        getString(R.string.login_error),
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

    private fun openHome() {

        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        findNavController().navigate(action)

    }

    private fun openRegister() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        findNavController().navigate(action)
    }
}

