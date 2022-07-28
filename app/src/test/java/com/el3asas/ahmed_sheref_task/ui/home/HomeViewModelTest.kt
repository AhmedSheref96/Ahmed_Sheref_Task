package com.el3asas.ahmed_sheref_task.ui.home

import com.el3asas.ahmed_sheref_task.models.AssociatedDrugItem
import com.el3asas.ahmed_sheref_task.models.ClassNameItem
import com.el3asas.ahmed_sheref_task.models.MedicationsClassesItem
import junit.framework.Assert.assertEquals
import org.junit.Test

internal class HomeViewModelTest {

    @Test
    fun mappingData() {
        val medicineList: List<MedicationsClassesItem?>? = listOf(
            MedicationsClassesItem(
                className2 = listOf(
                    ClassNameItem(
                        associatedDrug = listOf(AssociatedDrugItem()),
                        associatedDrug2 = listOf(AssociatedDrugItem())
                    )
                ),
                className = listOf(
                    ClassNameItem(
                        associatedDrug = listOf(AssociatedDrugItem()),
                        associatedDrug2 = listOf(AssociatedDrugItem())
                    )
                )
            )
        )

        assertEquals("failed", 4, medicineList?.size)

    }

}