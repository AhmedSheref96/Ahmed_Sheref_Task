package com.el3asas.ahmed_sheref_task.data.local.room

import com.el3asas.ahmed_sheref_task.models.AssociatedDrugItem
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class MedicineController @Inject constructor(private val dao: MedicineDao) {

    suspend fun insertMedicine(item: AssociatedDrugItem) = withContext(Dispatchers.IO) {
        dao.insertMedicine(item)
    }

}