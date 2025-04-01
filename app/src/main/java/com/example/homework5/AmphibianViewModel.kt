package com.example.homework5

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework5.model.AmphibianInfo
import com.example.homework5.network.AmphibianApi
import kotlinx.coroutines.launch

class AmphibianViewModel : ViewModel() {
    private val fetchedAmphibians = mutableStateOf<List<AmphibianInfo>>(emptyList())
    private val amphibians: State<List<AmphibianInfo>> = fetchedAmphibians

    fun getAmphibian(){
        viewModelScope.launch {
            try {
                val listResult = AmphibianApi.retrofitService.getAmphibians()
                fetchedAmphibians.value = listResult
            } catch (error: Exception) {
                fetchedAmphibians.value = emptyList()
            }
        }
    }
}