package com.el3asas.ahmed_sheref_task.data.network.problems

import com.el3asas.ahmed_sheref_task.models.ProblemsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProblemsService {

    @GET("v3/6e825792-030e-4c0a-bdd3-29b160507e0d")
    suspend fun getProblems(): Response<ProblemsResponse>

}