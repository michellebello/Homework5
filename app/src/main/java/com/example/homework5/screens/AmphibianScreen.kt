package com.example.homework5.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ){
        items(listOfAmphibians) { amphibian ->
            AmphibianCard(amphibian)
        }
    }
}

@Composable
fun AmphibianCard(amphibian: AmphibianInfo){
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0XFFc1c9c1)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start=15.dp, end=15.dp)
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = amphibian.name + " (" + amphibian.type +")",
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                modifier = Modifier.padding(10.dp)
                    .fillMaxWidth()
            )
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                contentDescription = amphibian.name,
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imageSrc)
                    .crossfade(true)
                    .build()
            )
            Text (
                text = amphibian.description,
                fontWeight = FontWeight.W300,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(10.dp)

            )
        }
    }
    Spacer(modifier = Modifier.size(25.dp))
}