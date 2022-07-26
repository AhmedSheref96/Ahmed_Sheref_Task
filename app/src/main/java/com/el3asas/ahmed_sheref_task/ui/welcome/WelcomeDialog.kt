package com.el3asas.ahmed_sheref_task.ui.welcome

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.el3asas.ahmed_sheref_task.databinding.DialogWelcomeBinding
import com.el3asas.utils.binding.DialogFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeDialog(override val bindingInflater: (LayoutInflater) -> ViewBinding = DialogWelcomeBinding::inflate) :
    DialogFragmentBinding<DialogWelcomeBinding>() {

    private val viewModel by viewModels<WelcomeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = this@WelcomeDialog.viewModel
            lifecycleOwner = this@WelcomeDialog
            executePendingBindings()
        }
        Handler().postDelayed({
            dismiss()
        }, 5000)
    }
}