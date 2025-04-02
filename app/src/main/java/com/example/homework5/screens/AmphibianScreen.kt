package com.example.homework5.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.homework5.model.AmphibianInfo


@Composable
fun AmphibianScreen(amphibianUIState: AmphibianUIState, afterAction: () -> Unit) {
    when (amphibianUIState) {
        is AmphibianUIState.Loading -> LoadingScreen()
        is AmphibianUIState.Success -> AmphibianTotalList(listOfAmphibians = amphibianUIState.amphibians)
        else -> ErrorScreen(afterAction)
    }

}

@Composable
fun LoadingScreen(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Loading"
        )
    }
}

@Composable
fun ErrorScreen(afterAction: () -> Unit){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Text("An error has occurred.")
        Button(
            onClick = afterAction
        ) {
            Text("Try again")
        }
    }
}

@Composable
fun AmphibianTotalList(listOfAmphibians: List<AmphibianInfo>){
    LazyColumn(
        modifier = Modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.Center
    ){
        items(listOfAmphibians) { amphibian ->
            AmphibianCard(amphibian)
        }
    }
}

@Composable
fun AmphibianCard(amphibian: AmphibianInfo){
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = amphibian.name
            )
            AsyncImage(
                contentDescription = amphibian.name,
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imageSrc)
                    .crossfade(true)
                    .build(),
                modifier = Modifier.fillMaxWidth()
            )
            Text (
                text = amphibian.description
            )
        }
    }
}