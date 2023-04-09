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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tidya.outfit

@Preview(showBackground = true)
@Composable
fun SearchScreen(){
    Scaffold {innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)){
            //verticalArrangement = Arrangement.Center,
            //horizontalAlignment = Alignment.CenterHorizontally) {
            //coding
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

            Text(text = "${inputValue.value}", modifier = Modifier.padding(top = 20.dp))

        }
    }
}