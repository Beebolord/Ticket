package com.example.ticket.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ticket.NavRoute


@Composable
fun Home(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var onUsernameChange = { text: String ->
        username = text
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.size(30.dp))
            Button(onClick = {
                navController.navigate(NavRoute.Ticket.route)
            }) {
                Text(text = "Ooooh we click me ")
            }

        }
    }
}