package com.example.tidya.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tidya.outfit
import java.net.URL
import java.util.concurrent.CompletableFuture
import java.util.function.Supplier

fun fda(): String {
    val apiUrl = "https://api.fda.gov/drug/label.json?search="
    val searchQuery = "Tylenol"

    // Build the API URL with the search query
    val url = URL(apiUrl + searchQuery)

    // Fetch the data from the API using CompletableFuture
    val completableFuture = CompletableFuture.supplyAsync(Supplier {
        try {
            val jsonString = url.readText()

            // Extract the "indications_and_usage" field from the JSON data
            val startIndex =
                jsonString.indexOf("\"indications_and_usage\": [") + "\"indications_and_usage\": [".length
            val endIndex = jsonString.indexOf("]", startIndex)

            // Return the extracted field from the JSON data
            jsonString.substring(startIndex, endIndex)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    })

    // Wait for the CompletableFuture to complete and return the result
    return completableFuture.get()
}

@Preview(showBackground = true)
@Composable
fun SearchScreen(){
    Scaffold {innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)){
            Text(text = "Search", modifier = Modifier.padding(top = 20.dp, start = 20.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = outfit,)

            val inputValue = remember {
                mutableStateOf("")
            }

            TextField(value = inputValue.value, onValueChange = { newValue -> inputValue.value = newValue }
                , modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                    .fillMaxWidth())

            Text(text = inputValue.value, modifier = Modifier.padding(top = 20.dp))

            Text(text = fda())

        }
    }
}