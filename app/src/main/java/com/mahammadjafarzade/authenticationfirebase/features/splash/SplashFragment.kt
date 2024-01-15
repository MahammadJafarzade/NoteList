package com.mahammadjafarzade.authenticationfirebase.features.splash

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Space
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.mahammadjafarzade.authenticationfirebase.databinding.FragmentSplashBinding
import com.mahammadjafarzade.authenticationfirebase.util.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint

class SplashFragment : Fragment() {

    val viewModel: SplashViewModel by viewModels()
    lateinit var binding: FragmentSplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater)
        // Inflate the layout for this fragment
        playLattie()
        return (binding.root)
    }

    private fun playLattie() {
        binding.animationView.repeatCount = 0
        binding.animationView.playAnimation()
        binding.animationView.addAnimatorListener(object : AnimatorListener {
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                checkAutoLogin()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }

        })
    }

    fun checkAutoLogin() {
        if (viewModel.isIntroPlayed) {
            if (FirebaseAuth.getInstance().currentUser != null) {
                val action = SplashFragmentDirections.actionSplashFragmentToNotesFragment()
                findNavController().navigate(action)
            } else {
                val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                findNavController().navigate(action)
            }
        } else {
            val action = SplashFragmentDirections.actionSplashFragmentToIntroFragment()
            findNavController().navigate(action)

            viewModel.saveIntroPassed()
        }
    }
//    private fun openApp(){
//        val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
//        findNavController().navigate(action)
//    }
}