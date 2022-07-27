package com.el3asas.ahmed_sheref_task.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.el3asas.ahmed_sheref_task.models.AssociatedDrugItem
import dagger.hilt.android.scopes.ViewModelScoped

@Dao
@ViewModelScoped
interface MedicineDao {

    @Insert
    suspend fun insertMedicine(item: AssociatedDrugItem)

    @Query("select * from AssociatedDrugItem")
    suspend fun getMedicines(): List<AssociatedDrugItem>

}