package com.el3asas.ahmed_sheref_task.ui.welcome

import androidx.lifecycle.ViewModel
import com.el3asas.ahmed_sheref_task.data.local.Pref
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val pref: Pref) : ViewModel() {
    val userName = pref.userName
}