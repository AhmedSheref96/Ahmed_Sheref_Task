package com.el3asas.ahmed_sheref_task.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.el3asas.ahmed_sheref_task.databinding.FragmentLoginBinding
import com.el3asas.utils.binding.FragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment(
    override val bindingInflater: (LayoutInflater) -> ViewBinding = FragmentLoginBinding::inflate
) : FragmentBinding<FragmentLoginBinding>() {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = this@LoginFragment.viewModel
            lifecycleOwner = this@LoginFragment
            executePendingBindings()
        }
    }

}