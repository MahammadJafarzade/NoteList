package com.mahammadjafarzade.authenticationfirebase.features.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.FirebaseError
import com.google.firebase.auth.FirebaseAuthActionCodeException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.auth
import com.mahammadjafarzade.authenticationfirebase.R
import com.mahammadjafarzade.authenticationfirebase.databinding.FragmentLoginBinding
import com.mahammadjafarzade.authenticationfirebase.databinding.FragmentRegisterBinding
import com.mahammadjafarzade.authenticationfirebase.util.FirebaseMessageHandler
import com.mahammadjafarzade.authenticationfirebase.util.showMessage


class LoginFragment : Fragment() {
    lateinit var binding : FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        return (binding.root)

        binding.loginButton.setOnClickListener{
            login()
        }
        binding.registerButton.setOnClickListener{
            openRegisterPage()
        }
    }
    fun login(){
        val firebaseAuth = Firebase.auth
        firebaseAuth.createUserWithEmailAndPassword(
            binding.usernameEditText.text.toString(),
            binding.passwordEditText.text.toString()
        ).addOnSuccessListener {
                openApp()
        }.addOnFailureListener{
            (it as? FirebaseAuthInvalidCredentialsException)?.errorCode?.let {
                //showMessage("hata",FirebaseMessageHandler.getLocalizedMessage(errorCode))
            }
        }
    }
    fun openApp(){
        val action = LoginFragmentDirections.actionLoginFragmentToNotesFragment()
        findNavController().navigate(action)
    }
    fun openRegisterPage(){
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        findNavController().navigate(action)
    }
}