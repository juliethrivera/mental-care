package com.laura0393.loign.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iegabrielamistral.mentalcare.AuthViewModel
import com.iegabrielamistral.mentalcare.ui.theme.MentalCareTheme
import com.laura0393.loign.ui.commons.NavigationItem


@Composable
fun SignUpScreen(navHostController:NavHostController, authViewModel: AuthViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Hi!", fontSize = 50.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        var Username by remember {
            mutableStateOf("Username")
        }
        TextField(
            label = {
                Text(text = "Username")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            value = Username,
            onValueChange = {
                Username = it
            })

        var email by remember {
            mutableStateOf("email")
        }
        TextField(
            label = {
                Text(text = "email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            value = email,
            onValueChange = {
                email = it
            })

        var password by remember {
            mutableStateOf("password")
        }
        TextField(
            label = {
                Text(text = "password")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            value = password,
            onValueChange = {
                password = it

            })
        var confirmpassword by remember {
            mutableStateOf("ConfirmPassword")
        }

        TextField(
            label = {
                Text(text = "Confirmpassword")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            value = confirmpassword,
            onValueChange = {confirmpassword = it

            })


    }

}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    MentalCareTheme {
        SignUpScreen(rememberNavController() , AuthViewModel())
    }

}
