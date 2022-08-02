package com.el3asas.ahmed_sheref_task.ui.home

import com.el3asas.ahmed_sheref_task.models.AssociatedDrugItem
import com.el3asas.ahmed_sheref_task.models.ClassNameItem
import com.el3asas.ahmed_sheref_task.models.MedicationsClassesItem

class DataFormatter {
    private val mapped = arrayListOf<AssociatedDrugItem>()

    fun mappingData(medicineList: List<MedicationsClassesItem?>?): List<AssociatedDrugItem> {
        medicineList?.filterNotNull()?.forEach { medicineItem ->
            val l0 = medicineItem.className2?.filterNotNull()
            val l1 = medicineItem.className?.filterNotNull()

            if (l0 != null) {
                mappingClassName(l0)
            }

            if (l1 != null) {
                mappingClassName(l1)
            }
        }
        return mapped
    }

    private fun mappingClassName(list: List<ClassNameItem>?) {
        list?.forEach { medicineItem ->
            val l0 = medicineItem.associatedDrug?.filterNotNull()
            val l1 = medicineItem.associatedDrug2?.filterNotNull()

            if (l0 != null) {
                mapped.addAll(l0)
            }

            if (l1 != null) {
                mapped.addAll(l1)
            }
        }
    }


}