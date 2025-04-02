package com.example.homework5

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homework5.screens.AmphibianScreen
import com.example.homework5.screens.AmphibianViewModel
import com.example.homework5.ui.theme.Homework5Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "App started")
        setContent {
            Homework5Theme {
                val appBackgroundColor = Color(0XFFf2f2eb)
                Scaffold (
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar (
                            title = {
                                Text("Amphibians", fontWeight = FontWeight.W400)
                            },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = appBackgroundColor)
                        )
                    }
                ) { innerPadding ->
                    Surface (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = appBackgroundColor
                    ){
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
}

