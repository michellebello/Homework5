package com.example.homework5.screens

import android.net.http.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.homework5.AmphibianApplication
import com.example.homework5.data.AmphibianRepository
import com.example.homework5.model.AmphibianInfo
import kotlinx.coroutines.launch
import java.io.IOException

class AmphibianViewModel (private val amphibianRepository : AmphibianRepository) : ViewModel(){
    var amphibianUIState: AmphibianUIState by mutableStateOf(AmphibianUIState.Loading)
        private set

    init{
        getAmphibian()
    }
    fun getAmphibian(){
        viewModelScope.launch {
            amphibianUIState = AmphibianUIState.Loading
            amphibianUIState = try {
               AmphibianUIState.Success(amphibianRepository.getAmphibians())
            } catch (e: IOException) {
                AmphibianUIState.Error
            } catch (e: HttpException) {
                AmphibianUIState.Error
            }
        }
    }

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as AmphibianApplication
                val amphibianRepository = application.container.amphibianRepository
                AmphibianViewModel(amphibianRepository = amphibianRepository)
            }
        }
    }
}

sealed interface AmphibianUIState {
    data class Success(val amphibians: List<AmphibianInfo>) : AmphibianUIState
    data object Loading: AmphibianUIState
    data object Error: AmphibianUIState
}