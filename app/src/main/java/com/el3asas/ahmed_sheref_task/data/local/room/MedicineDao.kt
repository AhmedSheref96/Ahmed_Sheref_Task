package com.el3asas.ahmed_sheref_task.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import com.el3asas.ahmed_sheref_task.models.AssociatedDrugItem
import dagger.hilt.android.scopes.ViewModelScoped

@Dao
@ViewModelScoped
interface MedicineDao {

    @Insert
    suspend fun insertMedicine(item: AssociatedDrugItem)

}