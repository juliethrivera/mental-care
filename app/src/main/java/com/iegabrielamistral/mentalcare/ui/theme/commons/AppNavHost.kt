package com.laura0393.loign.ui.commons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iegabrielamistral.mentalcare.AuthViewModel
import com.laura0393.loign.ui.screen.LoginScreen
import com.laura0393.loign.ui.screen.SignInScreen
import com.laura0393.loign.ui.screen.SignUpScreen

//Es la que te permite navegar por todas las pantallas
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: String,
    AuthViewModel: AuthViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
    composable(NavigationItem.Login.route){
        LoginScreen(navHostController, AuthViewModel)
    }
        composable(NavigationItem.Login.route){
            SignInScreen(navHostController,AuthViewModel)
        }
        composable(NavigationItem.Login.route){
            SignUpScreen(navHostController,AuthViewModel)
        }
        composable(NavigationItem.Login.route){
            //HomeScreen(navHostController,AuthViewModel)
        }
    }
}


