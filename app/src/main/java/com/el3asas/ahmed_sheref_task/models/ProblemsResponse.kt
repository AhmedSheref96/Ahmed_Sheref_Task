package com.el3asas.ahmed_sheref_task.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class ProblemsResponse(
    @Json(name = "problems")
    val problems: List<ProblemsItem?>? = null
)

@Entity
data class ProblemsItem(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @Json(name = "Diabetes")
    val diabetes: List<DiabetesItem?>? = null,

    @Json(name = "Asthma")
    val asthma: List<AsthmaItem?>? = null
)

data class DiabetesItem(

    @Json(name = "labs")
    val labs: List<LabsItem?>? = null,

    @Json(name = "medications")
    val medications: List<MedicationsItem?>? = null
)

data class AsthmaItem(
    val any: Any? = null
)


data class MedicationsClassesItem(
    @Json(name = "className2")
    val className2: List<ClassNameItem?>? = null,

    @Json(name = "className")
    val className: List<ClassNameItem?>? = null
)

data class MedicationsItem(

    @Json(name = "medicationsClasses")
    val medicationsClasses: List<MedicationsClassesItem?>? = null
)

data class ClassNameItem(

    @Json(name = "associatedDrug")
    val associatedDrug: List<AssociatedDrugItem?>? = null,

    @Json(name = "associatedDrug#2")
    val associatedDrug2: List<AssociatedDrugItem?>? = null
)

data class LabsItem(

    @Json(name = "missing_field")
    val missingField: String? = null
)

@Entity
@Parcelize
data class AssociatedDrugItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @Json(name = "dose")
    val dose: String? = null,

    @Json(name = "strength")
    val strength: String? = null,

    @Json(name = "name")
    val name: String? = null
) : Parcelable
