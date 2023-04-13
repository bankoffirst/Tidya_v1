package com.example.tidya.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tidya.outfit
import java.net.URL
import java.util.concurrent.CompletableFuture
import java.util.function.Supplier

fun fda(drugName: String): String {
    val apiUrl = "https://api.fda.gov/drug/label.json?search="
    val searchQuery = drugName

    val url = URL(apiUrl + searchQuery)

    val completableFuture = CompletableFuture.supplyAsync(Supplier {
        try {
            val jsonString = url.readText()

            val startIndex =
                jsonString.indexOf("\"indications_and_usage\": [") + "\"indications_and_usage\": [".length
            val endIndex = jsonString.indexOf("]", startIndex)
            jsonString.substring(startIndex, endIndex)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    })

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
                fontFamily = outfit)

            val inputValue = remember {
                mutableStateOf("")
            }

            OutlinedTextField(
                value = inputValue.value,
                onValueChange = { newValue -> inputValue.value = newValue },
                shape = RoundedCornerShape(40.dp),
                placeholder = { Text(text = "Enter a dung name.") },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color(0xff16C2D5),
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color(0xff16C2D5),
                    backgroundColor = Color.White),
                modifier = Modifier
                    .padding(start = 25.dp, end = 25.dp, top = 20.dp, bottom = 10.dp)
                    .fillMaxWidth()
                    //.height(20.dp)//Comment this
            )

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 50.dp)
                .background(
                    Color(0xffEBEBEB),
                    shape = RoundedCornerShape(20.dp)
                )
            ){

                if (inputValue.value == ""){

                } else{
                    if (fda(inputValue.value) == ""){
                        Text(text = "No drugs found.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        fontFamily = outfit,)
                    }else{
                        Column(modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize()) {
                            Text(text = inputValue.value, modifier = Modifier.padding(top = 20.dp, start = 20.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                fontFamily = outfit)
                            Text(text = "Indications and Usage", modifier = Modifier.padding(top = 5.dp, start = 20.dp, bottom = 0.dp, end = 20.dp),
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                fontFamily = outfit)
                            Text(text = fda(inputValue.value), modifier = Modifier.padding(top = 0.dp, start = 20.dp, end = 20.dp),
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                fontFamily = outfit)
                        }
                    }
                }
            }

        }
    }
}