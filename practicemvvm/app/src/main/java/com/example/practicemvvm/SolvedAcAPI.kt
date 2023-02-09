package com.example.practicemvvm

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SolvedAcAPI {
    @GET("/api/v3/user/show")
    suspend fun getUserData(
        @Query("handle")
        handler : String
    ) : Response<SolveAcGetUserDataModel>
}