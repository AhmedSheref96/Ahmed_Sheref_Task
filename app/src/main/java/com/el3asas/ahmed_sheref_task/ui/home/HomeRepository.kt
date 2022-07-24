package com.el3asas.ahmed_sheref_task.ui.home

import com.el3asas.ahmed_sheref_task.data.local.Pref
import javax.inject.Inject

class HomeRepository @Inject constructor(private val pref: Pref) {

    val userName = pref.userName

}