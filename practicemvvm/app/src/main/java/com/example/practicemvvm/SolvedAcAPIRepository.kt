package com.example.practicemvvm

class SolvedAcAPIRepository {
    private val solvedAcClient = GlobalApplication.baseService.create(SolvedAcAPI::class.java)

    suspend fun getUserData(handle : String) = solvedAcClient.getUserData(handle)
}