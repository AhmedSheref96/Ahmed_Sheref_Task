package com.el3asas.ahmed_sheref_task.ui.home

import com.el3asas.ahmed_sheref_task.models.AssociatedDrugItem
import com.el3asas.ahmed_sheref_task.models.ClassNameItem
import com.el3asas.ahmed_sheref_task.models.MedicationsClassesItem
import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.Test

class HomeViewModelTest {

    private val dataFormatter = DataFormatter()

    @Test
    fun `test mapping data from Api returns valid`() {
        val medicineList: List<MedicationsClassesItem?> = listOf(
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

        val data = dataFormatter.mappingData(medicineList)

        println(
            assertEquals(
                "failed test size is" +
                        "$data" +
                        " ${data.size}", data.size, 4
            )
        )

        println("test mapping data from Api returns valid")

    }

    @Test
    fun `test mapping data from Api returns fail`() {
        val medicineList: List<MedicationsClassesItem?> = listOf(
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

        val data = dataFormatter.mappingData(medicineList)

        println(
            assertEquals(
                "failed test size is" +
                        "$data" +
                        " ${data.size}", 8, data.size
            )
        )
    }

}