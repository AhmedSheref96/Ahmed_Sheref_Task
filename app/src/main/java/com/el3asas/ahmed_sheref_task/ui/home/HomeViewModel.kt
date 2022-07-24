package com.el3asas.ahmed_sheref_task.ui.home

import com.el3asas.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : BaseViewModel() {
    val userName = repository.userName

}