package com.example.jarprototype.domain

import com.example.jarprototype.model.EducationMetadataResponse
import com.example.jarprototype.network.JarApiInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JarRepository @Inject constructor(private val jarApiInterface: JarApiInterface) {
    suspend fun getEducationMetaData(): EducationMetadataResponse {
        return jarApiInterface.getEducationMetaData()
    }
}