package com.example.parkease

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.parkease.composables.SecondaryButton
import com.example.parkease.ui.theme.AppTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import com.example.parkease.composables.Carousel
import com.example.parkease.composables.ParkingGrid
import com.example.parkease.utilities.Location
import com.example.parkease.utilities.ParkingLotData
import com.example.parkease.utilities.User
import com.example.parkease.utilities.fetchCollection
import com.example.parkease.utilities.fetchDocument
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


@Composable
fun BookingPage(locationId: String, parkingSpaceId: String, navController: NavController) {
    var locationData by remember{ mutableStateOf<Location?>(null) }
    val coroutineScope = rememberCoroutineScope()

    // Call firebase API for fetching locationData
    LaunchedEffect(Unit) {
        fetchDocument(
            collectionName = "locations",
            documentId = locationId,
            type = Location::class.java,
            onSuccess = { data ->
                locationData = data
                println("Fetched locations: $locationData")
            },
            onFailure = { exception ->
                println("Error: ${exception.message}")
            }
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        // Mapping parking lot data
        // Later to be moved to a Page after user choose Location

        when {
            locationData == null -> Text(text = "Loading...")
            else -> {
                Text(
                    text = locationData!!.name,
                    style = AppTheme.typography.titleBig,
                    color = AppTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = parkingSpaceId,
                    style = AppTheme.typography.titleBig,
                    color = AppTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}