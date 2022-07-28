package com.el3asas.ahmed_sheref_task.ui.home

import com.el3asas.ahmed_sheref_task.data.di_modules.diLifeCycleAwaredModules
import com.el3asas.ahmed_sheref_task.data.di_modules.diSingletonModules
import com.el3asas.ahmed_sheref_task.models.AssociatedDrugItem
import com.el3asas.ahmed_sheref_task.models.ClassNameItem
import com.el3asas.ahmed_sheref_task.models.MedicationsClassesItem
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.Assert.assertEquals
import org.junit.Test
import javax.inject.Inject

@UninstallModules(diSingletonModules::class, diLifeCycleAwaredModules::class)
@HiltAndroidTest
internal class HomeViewModelTest {

    @Inject
    lateinit var homeViewModel: HomeViewModel

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

        assertEquals("failed", 4, homeViewModel.mappingData(medicineList))
        println("test succedded")

    }

}