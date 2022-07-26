package com.el3asas.ahmed_sheref_task.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.el3asas.ahmed_sheref_task.databinding.SheetDetailsBinding
import com.el3asas.utils.binding.BottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsBottomSheet(override val bindingInflater: (LayoutInflater) -> ViewBinding = SheetDetailsBinding::inflate) :
    BottomSheetBinding<SheetDetailsBinding>() {

    private val viewModel by viewModels<DetailsViewModel>()
    private val args by navArgs<DetailsBottomSheetArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = this@DetailsBottomSheet.viewModel
            lifecycleOwner = this@DetailsBottomSheet
            executePendingBindings()
        }

        viewModel.data.value = args.data
    }

}