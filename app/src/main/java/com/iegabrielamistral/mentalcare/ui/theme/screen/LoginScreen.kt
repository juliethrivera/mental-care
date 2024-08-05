package com.laura0393.loign.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.iegabrielamistral.mentalcare.AuthViewModel
import com.iegabrielamistral.mentalcare.R
import com.iegabrielamistral.mentalcare.ui.theme.MentalCareTheme
import com.laura0393.loign.ui.commons.NavigationItem


@Composable
fun LoginScreen(navHostController: NavHostController, authViewModel: AuthViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //Le añadimos una mimagen
        //para eso hacemos la imagen
        Image(
            painter = painterResource(id = R.drawable.login_image),
            contentDescription = "Imagen del inicio de sesion",
            modifier = Modifier.size(300.dp)
        )
        //Hacemos el color de la letra
        Text(
            text = "Hello", fontSize = 50.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

    }
    Spacer(modifier = Modifier.height(20.dp))

    Text(
        modifier = Modifier.padding(
            start = 50.dp,
            end = 50.dp
        ), text = "Welcome To Little Drop, where you manage you daily tasks"
    )

    //Hacemos el primer boton
    Spacer(modifier = Modifier.height(20.dp))

    Button(onClick = { /*TODO*/ }, modifier = Modifier.width(250.dp)) {
        Text(text = "Login")
    }
    //Hacemos el segundo boton

    Button(
        onClick = { navHostController.navigate(NavigationItem.SignIn.route) },
        border = BorderStroke(1.dp, Color.Black),
        colors = ButtonDefaults.outlinedButtonColors(),
        modifier = Modifier.width(250.dp)
    ) {
        Text(text = "Registrarse", color = Color.Black)
    }
    ElevatedButton(onClick = {}) {

    } 


}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MentalCareTheme {
        LoginScreen(rememberNavController(), AuthViewModel())
    }
}