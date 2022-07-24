package com.el3asas.ahmed_sheref_task.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.el3asas.ahmed_sheref_task.databinding.FragmentSplashBinding
import com.el3asas.utils.binding.FragmentBinding
import com.el3asas.utils.utils.navigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashFragment(override val bindingInflater: (LayoutInflater) -> ViewBinding = FragmentSplashBinding::inflate) :
    FragmentBinding<FragmentSplashBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            delay(2000)
            setupView()
        }
    }

    private fun setupView() {
        navigate(
            findNavController(),
            SplashFragmentDirections.actionSplashFragmentToLoginFragment()
        )
    }

}