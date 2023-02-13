package com.example.practicemvvm.data

import com.example.practicemvvm.config.GlobalApplication
import com.example.practicemvvm.domain.SolvedAcAPI

class SolvedAcAPIRepository {
    private val solvedAcClient = GlobalApplication.baseService.create(SolvedAcAPI::class.java)

    suspend fun getUserData(handle : String) = solvedAcClient.getUserData(handle)
}