package com.example.jarprototype.ui.theme.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jarprototype.domain.JarRepository
import com.example.jarprototype.model.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JarViewModel @Inject constructor(private val repository: JarRepository): ViewModel() {
    private var _getEducationMetaDataFlow: MutableStateFlow<Data?> = MutableStateFlow(null)
    var getEducationMetaDataFlow: StateFlow<Data?> = _getEducationMetaDataFlow.asStateFlow()
    fun getEducationMetaData() {
        viewModelScope.launch {
            try {
                val response = repository.getEducationMetaData()
                if(response.success) {
                    Log.d("JarViewModel", "getEducationMetaData api response: ${response.data}")
                    _getEducationMetaDataFlow.value = response.data
                } else {
                    Log.e("JarViewModel", "getEducationMetaData api error: ${response.success}")
                }
            } catch (e: Exception) {
                Log.e("JarViewModel", "getEducationMetaData exception: ${e.message}")
            }
        }
    }

    private var _expandedIndexFlow: MutableStateFlow<Int?> = MutableStateFlow(null)
    var expandedIndexFlow = _expandedIndexFlow.asStateFlow()

    fun setExpandedIndex(index: Int?) {
        _expandedIndexFlow.value = index
    }
}