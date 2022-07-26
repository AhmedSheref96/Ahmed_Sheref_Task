package com.el3asas.ahmed_sheref_task.ui.home

import android.view.View
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.el3asas.ahmed_sheref_task.data.local.room.MedicineController
import com.el3asas.ahmed_sheref_task.models.AssociatedDrugItem
import com.el3asas.ahmed_sheref_task.models.ClassNameItem
import com.el3asas.utils.base.BaseViewModel
import com.el3asas.utils.binding.RecyclerAdapterBinding
import com.el3asas.utils.utils.customSnackBar
import com.el3asas.utils.utils.getData
import com.el3asas.utils.utils.navigate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val medicineController: MedicineController
) : BaseViewModel(),
    RecyclerAdapterBinding.ItemClickListener {
    val userName = repository.userName

    val recyclerViewAdapter = MedicationsAdapter(this)

    fun getProblemsData(v: View) {
        viewModelScope.launch {
            getData(
                repository.getProblemsData(),
                onSuccess = {
                    it?.problems?.filterNotNull()?.let { list ->
                        val medicineList =
                            list[0].diabetes?.get(0)?.medications?.get(0)?.medicationsClasses

                        val d = medicineList?.filterNotNull()?.map { medicineList ->
                            val mapped = arrayListOf<ClassNameItem>()
                            medicineList.className2?.filterNotNull()
                                ?.let { it1 -> mapped.addAll(it1) }
                            medicineList.className?.filterNotNull()
                                ?.let { it1 -> mapped.addAll(it1) }
                            mapped
                        }?.map { classNameItems ->
                            val mapped = arrayListOf<AssociatedDrugItem>()
                            classNameItems.forEach {
                                mapped.addAll(it.associatedDrug?.filterNotNull() ?: emptyList())
                                mapped.addAll(it.associatedDrug2?.filterNotNull() ?: emptyList())
                            }
                            mapped
                        }

                        val data = arrayListOf<AssociatedDrugItem>()
                        d?.forEach {
                            data.addAll(it.toList())
                            data.addAll(it.toList())
                        }

                        recyclerViewAdapter.setData(data)
                    }
                },
                onError = {
                    customSnackBar(
                        v,
                        it,
                        com.el3asas.utils.R.drawable.ic_outline_error_outline_24
                    ) {}
                },
                isLoading
            )
        }
    }

    fun insertToDB(pos: Int) {
        val item =
            recyclerViewAdapter.getItem(pos)
        viewModelScope.launch {
            medicineController.insertMedicine(item)
        }
        recyclerViewAdapter.notifyItemChanged(pos, false)
    }

    override fun onItemClickListener(v: View, pos: Int) {
        val dir = HomeFragmentDirections.actionHomeFragmentToDetailsBottomSheet(
            recyclerViewAdapter.getItem(pos)
        )
        navigate(v.findNavController(), dir)
    }

}