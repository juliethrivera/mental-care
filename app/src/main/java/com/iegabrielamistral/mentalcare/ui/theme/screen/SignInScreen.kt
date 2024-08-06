package com.manuelnatera1.loign.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iegabrielamistral.mentalcare.AuthViewModel
import com.iegabrielamistral.mentalcare.R
import com.iegabrielamistral.mentalcare.ui.theme.MentalCareTheme


/**
 * Created by Carlos Jiménez on 24-Jun-24.
 */

@Composable
fun SignInScreen(navHostController: NavHostController, authViewModel: AuthViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_image),
            contentDescription = "Imagen del inicio de sesion",
            modifier = Modifier.size(300.dp)
        )

        Text(
            text = "Bienvenido!",
            fontSize = 50.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        var email by remember {
            mutableStateOf("")
        }

        OutlinedTextField(value = email, onValueChange = {
            email = it
        }, label = {
            Text("Email")
        }, placeholder = {
            Text("Enter your email")
        }, leadingIcon = {
            Icon(imageVector = Icons.Filled.Email, contentDescription = "Email Icon")
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email))

        Spacer(modifier = Modifier.height(20.dp))

        var password by remember {
            mutableStateOf("")
        }

        var showPassword by remember {
            mutableStateOf(false)
        }

        OutlinedTextField(value = password, onValueChange = {
            password = it
        }, label = {
            Text("Contraseña")
        }, placeholder = {
            Text("Ingresa tu contraseña")
        }, leadingIcon = {
            Icon(imageVector = Icons.Filled.Lock, contentDescription = "Password Icon")
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(percent = 20),
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }, trailingIcon = {
                if (showPassword) {
                    IconButton(onClick = { showPassword = false }) {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Show Password"
                        )
                    }
                } else {
                    IconButton(onClick = { showPassword = true }) {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Hide Password"
                        )
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.width(250.dp),
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            Text(text = "Iniciar sesión")
        }

    }
}


@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    MentalCareTheme{
        SignInScreen(rememberNavController(), AuthViewModel())
    }



}
