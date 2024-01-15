package com.mahammadjafarzade.authenticationfirebase.features.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.Firebase
import com.google.firebase.FirebaseError
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuthActionCodeException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.auth
import com.google.firebase.auth.FirebaseAuth
import com.mahammadjafarzade.authenticationfirebase.R
import com.mahammadjafarzade.authenticationfirebase.databinding.FragmentLoginBinding
import com.mahammadjafarzade.authenticationfirebase.databinding.FragmentRegisterBinding
import com.mahammadjafarzade.authenticationfirebase.util.FirebaseMessageHandler
import com.mahammadjafarzade.authenticationfirebase.util.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class LoginFragment : Fragment() {
    lateinit var binding : FragmentLoginBinding

    private var callbackManager: CallbackManager = CallbackManager.Factory.create()
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        binding.loginButtonfb.setOnClickListener {
            loginFacebook()
        }
        binding.loginButton.setOnClickListener{
            login()
        }
        binding.registerButton.setOnClickListener{

            openRegisterPage()
        }
        return (binding.root)


    }

    //email password
    fun login(){
        val firebaseAuth = Firebase.auth
        firebaseAuth.signInWithEmailAndPassword(
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

    //facebook
    fun loginFacebook() {
        //binding.loginButton.setReadPermissions(arrayListOf("email", "public_profile"))
        LoginManager.getInstance().registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    handleFacebookAccessToken(loginResult.accessToken)
                }

                override fun onCancel() {
                    Log.d("TAG", "facebook:onCancel")
                }

                override fun onError(error: FacebookException) {
                    Log.d("TAG", "facebook:onError", error)
                }
            },
        )

        LoginManager.getInstance().logInWithReadPermissions(this, callbackManager, PERMISSIONS)
    }

    val PERMISSIONS = listOf("public_profile", "email")

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {

            // callbackManager.onActivityResult(requestCode, resultCode, data)

        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("TAG", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    val user = auth.currentUser
                    openMain()
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        requireContext(),
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    //updateUI(null)
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
    fun openMain(){
        val action = LoginFragmentDirections.actionLoginFragmentToNotesFragment()
        findNavController().navigate(action)
    }
}