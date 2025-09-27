package com.example.jarprototype.network

import com.example.jarprototype.model.EducationMetadataResponse
import retrofit2.http.GET

interface JarApiInterface {
    @GET("_assets/shared/education-metadata.json")
    suspend fun getEducationMetaData(): EducationMetadataResponse
}