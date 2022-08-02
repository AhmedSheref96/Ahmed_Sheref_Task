package com.el3asas.ahmed_sheref_task.ui.home

import android.view.View
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.el3asas.ahmed_sheref_task.data.local.room.MedicineController
import com.el3asas.utils.base.BaseViewModel
import com.el3asas.utils.utils.getData
import com.el3asas.utils.utils.navigate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    val dataFormatter: DataFormatter,
    private val medicineController: MedicineController?
) : BaseViewModel(), MedicationsAdapter.MedicationItemClickListener {
    val userName = repository.userName

    val recyclerViewAdapter = MedicationsAdapter(this)
    private val _isError: MutableStateFlow<String?> = MutableStateFlow(null)
    val isError: StateFlow<String?> = _isError

    var welocomeScreenShowed = false

    init {
        getData()
    }

    val getWelcomeText: String
        get() {
            val calendar = Calendar.getInstance()
            return when (calendar.get(Calendar.HOUR_OF_DAY)) {
                in 5..12 -> "Good morning "
                in 12..18 -> "Good afternoon "
                else -> "Good evening "
            }
        }

    private fun getData() {
        viewModelScope.launch {
            getData(
                repository.getProblemsData(),
                onSuccess = {
                    it?.problems?.filterNotNull()?.let { list ->
                        val medicineList =
                            list[0].diabetes?.get(0)?.medications?.get(0)?.medicationsClasses

                        recyclerViewAdapter.setData(dataFormatter.mappingData(medicineList))
                    }
                },
                onError = {
                    _isError.value = it
                },
                isLoading
            )
        }
    }


    fun insertToDB(pos: Int) {
        val item =
            recyclerViewAdapter.getItem(pos)
        viewModelScope.launch {
            medicineController?.insertMedicine(item)
        }
        recyclerViewAdapter.notifyItemChanged(pos, false)
    }

    fun openSaved(v: View) {
        navigate(v.findNavController(), HomeFragmentDirections.actionHomeFragmentToSavedFragment())
    }

    override fun onItemClick(v: View, position: Int) {
        val dir = HomeFragmentDirections.actionHomeFragmentToDetailsBottomSheet(
            recyclerViewAdapter.getItem(position)
        )
        navigate(v.findNavController(), dir)
    }

    override fun onInsertItemClick(v: View, pos: Int) {
        insertToDB(pos)
        Toast.makeText(v.context, "Item Inserted", Toast.LENGTH_LONG).show()
    }

}
