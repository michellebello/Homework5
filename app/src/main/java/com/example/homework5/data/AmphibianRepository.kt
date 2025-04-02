package com.example.homework5.data

import com.example.homework5.model.AmphibianInfo
import com.example.homework5.network.AmphibianApiService

interface AmphibianRepository {
    suspend fun getAmphibians() : List<AmphibianInfo>
}

class DefaultAmphibianRepository (
    private val amphibianApiService : AmphibianApiService
) : AmphibianRepository {
    override suspend fun getAmphibians() : List<AmphibianInfo> = amphibianApiService.getAmphibians()
}