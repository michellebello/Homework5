package com.example.homework5.network
import com.example.homework5.model.AmphibianInfo
import retrofit2.http.GET

interface AmphibianApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<AmphibianInfo>
}
