package com.el3asas.ahmed_sheref_task.ui.saved

import android.view.View
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.el3asas.ahmed_sheref_task.data.local.room.MedicineController
import com.el3asas.utils.base.BaseViewModel
import com.el3asas.utils.binding.RecyclerAdapterBinding
import com.el3asas.utils.utils.getData
import com.el3asas.utils.utils.navigate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(private val controller: MedicineController) :
    BaseViewModel(),
    RecyclerAdapterBinding.ItemClickListener {

    private val _isError: MutableStateFlow<String?> = MutableStateFlow(null)
    val isError: StateFlow<String?> = _isError

    val adapter = MedicationsAdapter(this)

    init {
        getSavedData()
    }

    private fun getSavedData() {
        viewModelScope.launch {
            getData(controller.getMedicines(), onSuccess = {
                adapter.setData(it)
            }, onError = {
                _isError.value = it
            }, isLoading)
        }
    }

    override fun onItemClickListener(v: View, pos: Int) {
        navigate(
            v.findNavController(),
            SavedFragmentDirections.actionSavedFragmentToDetailsBottomSheet(adapter.getItem(pos))
        )
    }

}