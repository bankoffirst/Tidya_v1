package com.example.tidya.presentation

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tidya.bottomnav.BottomNav
import com.example.tidya.model.AuthViewModel
import com.example.tidya.outfit
import com.example.tidya.utils.AuthResultContract
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalMaterialApi
@Composable
fun AuthView(errorText:String?, onClick:() -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xff16C2D5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Tidya",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            fontFamily = outfit,
            modifier = Modifier.padding(top = 160.dp)
        )

        GoogleSignInButtonUi(onClicked = {onClick()})
            errorText?.let {
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = it)
            }

    }
}



@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun AuthScreen(authViewModel: AuthViewModel){

    val coroutineScope = rememberCoroutineScope()
    var text by remember { mutableStateOf<String?>(null) }
    val user by remember (authViewModel){authViewModel.user}.collectAsState()
    val signInRequestCode = 1

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()){
                task ->
                try {
                    val account = task?.getResult(ApiException::class.java)
                    if (account==null){
                        text = "Google sign in failed"
                    }else{
                        coroutineScope.launch {
                            account.email?.let { account.displayName?.let { it1 -> authViewModel.signIn(email = it,displayName = it1) } }
                        }
                    }
                }catch (e:ApiException) {
                    text = "Google sign in failed"
                }
        }
    AuthView(errorText = text,onClick = {text=null
        authResultLauncher.launch(signInRequestCode)
    })
    user?.let{
        BottomNav(user = it)
    }
}