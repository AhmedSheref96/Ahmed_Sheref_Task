package com.el3asas.ahmed_sheref_task.data.network.problems

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProblemsClient @Inject constructor(private val service: ProblemsService) {
    suspend fun getProblems() = withContext(Dispatchers.IO) {
        service.getProblems()
    }
}