package com.el3asas.ahmed_sheref_task.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.el3asas.ahmed_sheref_task.models.AssociatedDrugItem


@Database(entities = [AssociatedDrugItem::class], version = 2, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao
}