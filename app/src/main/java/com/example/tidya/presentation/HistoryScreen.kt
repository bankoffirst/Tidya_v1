package com.example.tidya.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
/*
@Composable
fun HistoryScreen() {
    Scaffold {innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Hi ${user.displayName}",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h4,
                fontSize = 30.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = user.email)
        }
    }
}

 */
@Composable
fun HistoryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "History Screen", fontSize = 20.sp)
    }
}