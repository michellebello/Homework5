package com.example.homework5

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homework5.screens.AmphibianScreen
import com.example.homework5.screens.AmphibianViewModel
import com.example.homework5.ui.theme.Homework5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "App started")
        setContent {
            Homework5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val amphibianViewModel: AmphibianViewModel =
                        viewModel(factory = AmphibianViewModel.Factory)
                    AmphibianScreen(
                        amphibianUIState = amphibianViewModel.amphibianUIState,
                        afterAction = amphibianViewModel::getAmphibian
                    )
                }
            }
        }
    }
}

