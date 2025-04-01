package com.example.homework5

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable


@Composable
fun AmphibianScreen() {
    Column {
        val amphibianViewModel = AmphibianViewModel()
        amphibianViewModel.getAmphibian()
    }
}