package com.laura0393.loign.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iegabrielamistral.mentalcare.AuthViewModel
import com.iegabrielamistral.mentalcare.R
import com.iegabrielamistral.mentalcare.ui.theme.MentalCareTheme


@Composable
fun SignInScreen(navHostController: NavHostController, authViewModel: AuthViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//le añadimos una imagen
//para eso acemos la imagen
        Image(
            painter = painterResource(id = R.drawable.login_image),
            contentDescription = "imagen de inicio de sesion",
            modifier = Modifier.size(300.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Sign In", fontSize = 50.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        // hacemos el email para los usuarios

        Spacer(modifier = Modifier.height(20.dp))
        var email by remember {
            mutableStateOf("email")
        }

        TextField(label = {
            Text(text = "email")
        },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            value = email,
            onValueChange = {
                email = it
            })
        // hacemos la comtraseña para el email

        Spacer(modifier = Modifier.height(20.dp))
        var password by remember {
            mutableStateOf("password")
        }

        TextField(label = {
            Text(text = "Password")
        },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            value = password,
            onValueChange = {
                password = it
            })

    }
}


@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    MentalCareTheme{
        SignInScreen(rememberNavController(), AuthViewModel())
    }



}
