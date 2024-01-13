package com.mahammadjafarzade.authenticationfirebase.features.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.mahammadjafarzade.authenticationfirebase.R
import com.mahammadjafarzade.authenticationfirebase.databinding.FragmentRegisterBinding
import com.mahammadjafarzade.authenticationfirebase.util.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    lateinit var binding : FragmentRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater)

        binding.registerButton.setOnClickListener{
            register()
        }
        return (binding.root)

    }
    fun register(){
        val firebaseAuth = Firebase.auth
        firebaseAuth.createUserWithEmailAndPassword(
            binding.emailEditText.text.toString(),
            binding.registerPasswordEditText.text.toString()
        ).addOnSuccessListener {
            findNavController().popBackStack()
        }.addOnFailureListener{
            println(it)
           // showMessage("hata")
        }
    }
}