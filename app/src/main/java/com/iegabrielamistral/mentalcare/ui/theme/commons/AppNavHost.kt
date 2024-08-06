package com.manuelnatera1.loign.ui.commons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iegabrielamistral.mentalcare.AuthViewModel
import com.manuelnatera1.login.ui.screens.SignUpScreen
import com.manuelnatera1.loign.ui.screen.LoginScreen
import com.manuelnatera1.loign.ui.screen.SignInScreen

//Es la que te permite navegar por todas las pantallas
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: String,
    authViewModel: AuthViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
    composable(NavigationItem.Login.route){
        LoginScreen(navHostController, authViewModel)
    }
        composable(NavigationItem.SignIn.route){
            SignInScreen(navHostController,authViewModel)
        }
        composable(NavigationItem.SignUp.route){
            SignUpScreen(navController = navHostController, authViewModel)
        }
        composable(NavigationItem.Home.route){
            //HomeScreen(navHostController,AuthViewModel)
        }
    }
}


