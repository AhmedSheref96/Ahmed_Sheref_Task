package com.el3asas.ahmed_sheref_task.ui.welcome

import androidx.lifecycle.ViewModel
import com.el3asas.ahmed_sheref_task.data.local.Pref
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val pref: Pref) : ViewModel() {
    val userName = pref.userName

    val getWelcomeText: String
        get() {
            val calendar = Calendar.getInstance()
            return when (calendar.get(Calendar.HOUR_OF_DAY)) {
                in 5..12 -> "Good morning "
                in  12..18 -> "Good afternoon "
                else -> "Good evening "
            }
        }
}