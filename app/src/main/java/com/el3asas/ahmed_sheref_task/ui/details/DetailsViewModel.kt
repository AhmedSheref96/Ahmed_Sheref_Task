package com.el3asas.ahmed_sheref_task.ui.details

import android.view.View
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModel
import com.el3asas.ahmed_sheref_task.models.AssociatedDrugItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {
    var data: MutableStateFlow<AssociatedDrugItem?> = MutableStateFlow(null)
    fun cancelClick(v: View) {
        val fragment = v.findFragment<BottomSheetDialogFragment>()
        fragment.dismiss()
    }
}