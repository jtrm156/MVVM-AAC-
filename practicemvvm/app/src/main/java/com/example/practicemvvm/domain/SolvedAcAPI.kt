package com.example.practicemvvm.domain

import com.example.practicemvvm.data.SolveAcGetUserDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SolvedAcAPI {
    @GET("/api/v3/user/show")
    suspend fun getUserData(
        @Query("handle")
        handle : String
    ) : Response<SolveAcGetUserDataModel>
}