package com.el3asas.ahmed_sheref_task.ui.home

import com.el3asas.ahmed_sheref_task.data.local.Pref
import com.el3asas.ahmed_sheref_task.data.network.problems.ProblemsClient
import com.el3asas.utils.utils.Response
import com.el3asas.utils.utils.safeCall
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val client: ProblemsClient,
    private val pref: Pref?
) {
    suspend fun getProblemsData() = safeCall {
        val response = client.getProblems()
        when (response.isSuccessful) {
            true -> {
                return@safeCall Response.Success(response.body())
            }
            else -> {
                return@safeCall Response.Error(response.raw().message)
            }
        }
    }

    val userName = pref?.userName

}