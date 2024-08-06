package com.iegabrielamistral.mentalcare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.iegabrielamistral.mentalcare.ui.theme.MentalCareTheme
import com.manuelnatera1.loign.ui.commons.AppNavHost
import com.manuelnatera1.loign.ui.commons.NavigationItem

class MainActivity2 : ComponentActivity() {

    val authViewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MentalCareTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(
                        navHostController = rememberNavController(),
                        startDestination = NavigationItem.Login.route,
                        authViewModel = authViewModel
                    )
                }
            }
        }
    }
}