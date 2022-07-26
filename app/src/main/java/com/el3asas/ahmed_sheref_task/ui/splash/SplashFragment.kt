package com.el3asas.ahmed_sheref_task.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.el3asas.ahmed_sheref_task.R
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
//        lifecycleScope.launchWhenCreated {
//            delay(2000)
//            setupView()
//        }

        binding.constraintLayout2.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {}

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                if (currentId== R.id.end){
                    setupView()
                }
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
            }
        })
    }

    private fun setupView() {
        navigate(
            findNavController(),
            SplashFragmentDirections.actionSplashFragmentToLoginFragment()
        )
    }

}