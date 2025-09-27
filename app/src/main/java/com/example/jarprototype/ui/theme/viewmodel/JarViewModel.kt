package com.example.jarprototype.ui.theme.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jarprototype.domain.JarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JarViewModel @Inject constructor(private val repository: JarRepository): ViewModel() {
    fun getEducationMetaData() {
        viewModelScope.launch {
            try {
                val response = repository.getEducationMetaData()
                if(response.success) {
                    Log.d("JarViewModel", "getEducationMetaData api response: ${response.data}")
                } else {
                    Log.e("JarViewModel", "getEducationMetaData api error: ${response.success}")
                }
            } catch (e: Exception) {
                Log.e("JarViewModel", "getEducationMetaData exception: ${e.message}")
            }
        }
    }
}