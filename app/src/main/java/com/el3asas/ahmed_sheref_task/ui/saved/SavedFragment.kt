package com.el3asas.ahmed_sheref_task.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.el3asas.ahmed_sheref_task.databinding.FragmentSavedBinding
import com.el3asas.utils.binding.FragmentBinding
import com.el3asas.utils.utils.customSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SavedFragment(override val bindingInflater: (LayoutInflater) -> ViewBinding = FragmentSavedBinding::inflate) :
    FragmentBinding<FragmentSavedBinding>() {

    private val viewModel by viewModels<SavedViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = this@SavedFragment.viewModel
            lifecycleOwner = this@SavedFragment
            executePendingBindings()
        }

        lifecycleScope.launchWhenResumed {
            viewModel.isError.collectLatest {
                if (it != null) {
                    customSnackBar(
                        requireView(),
                        it,
                        com.el3asas.utils.R.drawable.ic_outline_error_outline_24
                    ) {}
                }
            }
        }
    }

}